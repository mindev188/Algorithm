class Solution {
    public String solution(String my_string) {
        String answer = "";
        my_string = my_string.toLowerCase();
        char[] arr = my_string.toCharArray();
        quickSort(arr, 0, arr.length - 1);

        return String.valueOf(arr);
    }

    /**
     * 분할 정렬
     * 1. 피벗 선택 : 마지막 원소를 피벗으로 선택
     * 2. 분할(Partition) : 피벗보다 작은 원소들은 왼쪽, 큰 원소들은 오른쪽으로 이동
     * 3. 재귀 호출 : 분할된 두 부분 배열에 대한 퀵 정렬을 재귀적 수행
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(char[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * 분할 및 정렬
     * 중앙값 반환
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public int partition(char[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}