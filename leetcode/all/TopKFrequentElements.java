package leetcode.all;

import java.util.*;

public class TopKFrequentElements {
    private int[] topKFrequent(int[] nums, int k) {
        int[] topKElements = new int[k];

        int maxFrequency = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, List<Integer>> frequencyBuckets = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int f = freqMap.getOrDefault(nums[i],0);

            freqMap.put(nums[i], f+1);
            maxFrequency = Math.max(f+1, maxFrequency);
        }

        for (int num: freqMap.keySet()) {
            int freq = freqMap.get(num);
            frequencyBuckets.computeIfAbsent(freq, x -> new ArrayList<>()).add(num);
        }

        int counter = 0;
        while(maxFrequency > 0 && counter < k) {
            List<Integer> freqBucket = frequencyBuckets.get(maxFrequency--);
            
            if (freqBucket != null) {
                for (int i = 0; i < freqBucket.size() && counter < k; i++) {
                    topKElements[counter++] = freqBucket.get(i);
                }
            }
        }

        return topKElements;
    }
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        int[] result = new int[]{};

        result = solution.topKFrequent(new int[]{1,1,1,2,2,3}, 2);

        for (int i = 0; i < result.length; i++) {
            System.out.printf("%d ", result[i]);
        }
    }
}
