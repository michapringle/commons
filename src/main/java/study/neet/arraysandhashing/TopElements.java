package study.neet.arraysandhashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class TopElements {

    // 3:27 -> 4:25
    static int[] topKFrequent(final int[] nums, final int k) {

        final Map<Integer, Integer> numAndCounts = new TreeMap<>();

        Arrays
                .stream(nums)
                .forEach(num -> {
                    if (!numAndCounts.containsKey(num)) {
                        numAndCounts.put(num, 0);
                    }
                    numAndCounts.put(num, numAndCounts.get(num) + 1);
                });

        final List<int[]> countAndNums = new ArrayList<>();
        numAndCounts.forEach((key, value) -> countAndNums.add(new int[]{value, key}));

        countAndNums.sort((o1, o2) -> o2[0] - o1[0]);

        return countAndNums
                .subList(0, k)
                .stream()
                .mapToInt(i -> i[1])
                .toArray();
    }
}
