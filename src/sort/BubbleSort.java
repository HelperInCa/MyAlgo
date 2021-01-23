package sort;

public class BubbleSort {
    public static void main(String[] args) {

    }
    // 逆序
    public static void bubbleSort(int[] arr) {
        // 时间复杂度 O(n^2)
        int temp = 0;
        boolean flag = false; // 表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}
