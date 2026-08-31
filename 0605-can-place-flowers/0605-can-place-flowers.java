class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int posibleCount = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) continue;

            int left = 0;
            int right = 0;

            if (i > 0 && flowerbed[i - 1] == 1) left = 1;
            if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) right = 1;

            if (left != 1 && right != 1) {
                posibleCount++;
                flowerbed[i] = 1;
            }
        }

        return posibleCount >= n;
    }
}