package utils;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;

import com.sun.management.OperatingSystemMXBean;

/**
 * Utility to run a callback and return the callback's result plus simple
 * metrics: wall (execution) time, process CPU time, CPU %, and heap before/after.
 *
 * New: pretty printing and overloads that optionally print the PerfResult.
 */
public final class PerfUtil {
    private PerfUtil() {}

    public static class PerfResult<T> {
        public final T result;
        public final long wallTimeNs;
        public final long cpuTimeNs;
        public final double cpuPercent; // 0..100
        public final long heapBeforeBytes;
        public final long heapAfterBytes;
        public final long heapUsedBytes; // new: heapAfter - heapBefore

        public PerfResult(T result, long wallTimeNs, long cpuTimeNs, double cpuPercent,
                          long heapBeforeBytes, long heapAfterBytes, long heapUsedBytes) {
            this.result = result;
            this.wallTimeNs = wallTimeNs;
            this.cpuTimeNs = cpuTimeNs;
            this.cpuPercent = cpuPercent;
            this.heapBeforeBytes = heapBeforeBytes;
            this.heapAfterBytes = heapAfterBytes;
            this.heapUsedBytes = heapUsedBytes;
        }

        /**
         * Pretty multi-line string representation (blue).
         */
        public String prettyString() {
            final String BLUE = "\u001B[34m";
            final String RESET = "\u001B[0m";
            return String.format(
                BLUE +
                "PerfResult {\n" +
                "  result        : %s\n" +
                "  wallTime      : %.3f ms\n" +
                "  cpuTime       : %.3f ms\n" +
                "  cpuPercent    : %.2f %%\n" +
                "  heapBefore    : %.3f MB\n" +
                "  heapAfter     : %.3f MB\n" +
                "  heapUsed      : %.3f MB\n" +
                "}" +
                RESET,
                String.valueOf(result),
                wallTimeNs / 1_000_000.0,
                cpuTimeNs / 1_000_000.0,
                cpuPercent,
                heapBeforeBytes / 1024.0 / 1024.0,
                heapAfterBytes / 1024.0 / 1024.0,
                heapUsedBytes / 1024.0 / 1024.0
            );
        }

        @Override
        public String toString() {
            return prettyString();
        }
    }

    // Internal implementation used by public overloads.
    private static <T> PerfResult<T> runInternal(Callable<T> task) throws Exception {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        int procs = Math.max(1, os.getAvailableProcessors());
        Runtime rt = Runtime.getRuntime();

        long heapBefore = rt.totalMemory() - rt.freeMemory();
        long wall0 = System.nanoTime();
        long cpu0 = os.getProcessCpuTime();

        T result = task.call();

        long wall1 = System.nanoTime();
        long cpu1 = os.getProcessCpuTime();
        long heapAfter = rt.totalMemory() - rt.freeMemory();

        long wallDelta = Math.max(1, wall1 - wall0);
        long cpuDelta = Math.max(0, cpu1 - cpu0);
        double cpuPct = (double) cpuDelta / (double) wallDelta / procs * 100.0;
        long heapUsed = Math.max(0L, heapAfter - heapBefore);

        return new PerfResult<>(result, wallDelta, cpuDelta, cpuPct, heapBefore, heapAfter, heapUsed);
    }

    /**
     * Run a Callable and return PerfResult containing the callable's result and metrics.
     * Caller is responsible for handling any exception thrown by the callable.
     * Does not print by default.
     */
    public static <T> PerfResult<T> run(Callable<T> task) throws Exception {
        return run(task, false);
    }

    /**
     * Run a Callable and optionally print the pretty PerfResult to stdout.
     */
    public static <T> PerfResult<T> run(Callable<T> task, boolean print) throws Exception {
        PerfResult<T> res = runInternal(task);
        if (print) {
            System.out.println(res.prettyString());
        }
        return res;
    }

    /**
     * Convenience for Runnable tasks (no returned value). Does not print by default.
     */
    public static PerfResult<Void> run(Runnable task) {
        return run(task, false);
    }

    /**
     * Convenience for Runnable tasks (no returned value) with optional printing.
     */
    public static PerfResult<Void> run(Runnable task, boolean print) {
        try {
            return run(() -> { task.run(); return null; }, print);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}