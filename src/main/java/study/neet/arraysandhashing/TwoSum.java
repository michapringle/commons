package study.neet.arraysandhashing;

import java.util.HashMap;
import java.util.Map;

final class TwoSum {

    private TwoSum() {
    }

    static int[] twoSum(final int[] nums, final int total) {

        return twoSumFaster(nums, total);
    }

    private static int[] twoSumSlower(final int[] nums, final int total) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == total)
                    return new int[]{i, j};
            }
        }

        return new int[]{};
    }

    // see https://stackoverflow.com/a/65944513
    private static int[] twoSumFaster(final int[] nums, final int total) {

        final Map<Integer, Integer> otherNums = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            otherNums.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            final int otherNum = total - nums[i];
            if (otherNums.containsKey(otherNum) && otherNums.get(otherNum) != i) {
                return new int[]{i, otherNums.get(otherNum)};
            }
        }

        return new int[]{};
    }
}

