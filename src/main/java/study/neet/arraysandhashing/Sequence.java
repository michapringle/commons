package study.neet.arraysandhashing;

import java.util.HashSet;
import java.util.Set;

final class Sequence {

    private Sequence() {
    }

    /**
     * Given an array of integers nums, return the length of the longest consecutive
     * sequence of elements.
     * <p>
     * A consecutive sequence is a sequence of elements in which each element
     * is exactly 1 greater than the previous element.
     * <p>
     * You must write an algorithm that runs in O(n) time.
     */
    // 9:30 -> 11:33 -- looked up solution
    static int longestConsecutive(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        final Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (final int n : numSet) {

            if (!numSet.contains(n - 1)) {
                int length = 1;
                while (numSet.contains(n + length)) {
                    length++;
                }
                longest = Math.max(length, longest);
            }
        }
        return longest;
    }
}
