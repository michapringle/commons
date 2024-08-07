package study.neet.arraysandhashing;

final class ArrayProduct {

    private ArrayProduct() {
    }

    // Given an integer array nums, return an array output where output[i] is
    // the product of all the elements of nums except nums[i].
    // Each product is guaranteed to fit in a 32-bit integer.
    // Follow-up: Could you solve it in O(n) time without using the division operation?

    // 3:16 -> 4:47, this included look-up of fast solution, slow solution was easy
    static int[] productExceptSelf(int[] nums) {

        return productExceptSelfFast(nums);
    }

    static int[] productExceptSelfFast(int[] nums) {

        int[] leftProducts = new int[nums.length - 1];
        int[] rightProducts = new int[nums.length - 1];
        int[] results = new int[nums.length];

        for (int i = 0; i < nums.length - 1; i++) {
            int multiplier = i == 0 ? 1 : leftProducts[i - 1];
            leftProducts[i] = nums[i] * multiplier;
        }

        for (int i = nums.length - 1; i > 0; i--) {
            int multiplier = i == nums.length - 1 ? 1 : rightProducts[i];
            rightProducts[i - 1] = nums[i] * multiplier;
        }

        results[0] = rightProducts[0];
        results[nums.length - 1] = leftProducts[leftProducts.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            results[i] = leftProducts[i - 1] * rightProducts[i];
        }

        return results;
    }

    private static int[] productExceptSelfSlow(int[] nums) {

        int[] products = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            products[i] = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    products[i] *= nums[j];
                }
            }
        }

        return products;
    }
}
