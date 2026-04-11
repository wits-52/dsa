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
    private Map<Integer, List<Job>> jobsAtThisTime = new HashMap<>();
    List<Integer> startTimes;

    private Integer findNextStartingPoint(int time) {
        int start = 0, end = startTimes.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (this.startTimes.get(mid) == time) {
                return time;
            }

            if (startTimes.get(mid) < time) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (this.startTimes.get(start) < time) return null;

        return this.startTimes.get(start);
    }
    private void getJobsBySortedStartTime(int[] startTime, int[] endTime, int[] profit) {
        this.jobsAtThisTime.clear();

        for (int i = 0; i < startTime.length; i++) {
            Job j = new Job(startTime[i], endTime[i], profit[i]);

            this.jobsAtThisTime.computeIfAbsent(startTime[i], k -> new ArrayList<>()).add(j);
        }

        this.startTimes = new ArrayList<>(this.jobsAtThisTime.keySet());
        this.startTimes.sort((a, b) -> a - b);
    }
    private int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Map<Integer, Integer> maxProfitAtTime = new HashMap<>();
        this.getJobsBySortedStartTime(startTime, endTime, profit);

        for (int i = this.startTimes.size() - 1; i >= 0; i--) {
            int time = this.startTimes.get(i);

            List<Job> jobs = this.jobsAtThisTime.get(time);

            int maxProfit = 0;
            for (Job job: jobs) {
                Integer nextStartingTimeAfterThisJob = this.findNextStartingPoint(job.end);
                Integer nextStartTimeIfNotExecuted = this.findNextStartingPoint(job.start + 1);

                int jobProfit = job.profit, nextJobProfit = 0;

                if (nextStartingTimeAfterThisJob != null) {
                    jobProfit += maxProfitAtTime.getOrDefault(nextStartingTimeAfterThisJob, 0);
                }
                if (nextStartTimeIfNotExecuted != null) {
                    nextJobProfit += maxProfitAtTime.getOrDefault(nextStartTimeIfNotExecuted, 0);
                }

                maxProfit = Math.max(maxProfit, Math.max(jobProfit, nextJobProfit));
            }

            maxProfitAtTime.put(time, maxProfit);

        }

        return maxProfitAtTime.get(this.startTimes.get(0));
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
