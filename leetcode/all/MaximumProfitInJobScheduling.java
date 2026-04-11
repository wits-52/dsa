package leetcode.all;

import java.util.*;

public class MaximumProfitInJobScheduling {
    class Job {
        int start;
        int end;
        int profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    private List<Job> jobs;

    private Job findNextJob(int time) {
        int start = 0, end = this.jobs.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (this.jobs.get(mid).start < time) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (this.jobs.get(start).start < time) return null;

        return this.jobs.get(start);
    }
    private void getJobsBySortedStartTime(int[] startTime, int[] endTime, int[] profit) {
        this.jobs = new ArrayList<>();

        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        this.jobs.sort((a, b) -> a.start - b.start);
    }
    private int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        this.getJobsBySortedStartTime(startTime, endTime, profit);

        for (int i = this.jobs.size() - 1; i >= 0; i--) {
            Job job = this.jobs.get(i);

            Job nextJobAfterThisEnds = this.findNextJob(job.end);

            if (nextJobAfterThisEnds != null) {
                job.profit += nextJobAfterThisEnds.profit;
            }
            if (i != this.jobs.size() - 1) {
                job.profit = Math.max(job.profit, this.jobs.get(i+1).profit);
            }

        }

        return this.jobs.get(0).profit;
    }

    public static void main(String[] args) {
        MaximumProfitInJobScheduling solution = new MaximumProfitInJobScheduling();

        System.out.println(solution.jobScheduling(
            new int[]{1,2,3,3},
            new int[]{3,4,5,6},
            new int[]{50,10,40,70}
        )); // 120


        System.out.println(solution.jobScheduling(
            new int[]{1,2,3,4,6},
            new int[]{3,5,10,6,9},
            new int[]{20,20,100,70,60}
        )); // 150

        System.out.println(solution.jobScheduling(
            new int[]{1,1,1},
            new int[]{2,3,4},
            new int[]{5,6,4}
        )); // 6
    }
}
