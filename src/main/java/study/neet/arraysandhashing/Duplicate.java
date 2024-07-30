package study.neet.arraysandhashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final class Duplicate {

    private Duplicate() {
    }

    static boolean hasDuplicate(final int[] nums) {

        final Set<Integer> s = new HashSet<>();
        Arrays.stream(nums).forEach(s::add);
        return s.size() < nums.length;
    }
}

