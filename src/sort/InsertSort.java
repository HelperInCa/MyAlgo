package sort;

public class InsertSort {
    public static void main(String[] args) {

    }

    public static void insertSort(int[] arr) {
        int insertVal, insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;// 待插入数前一个数的下标
            // insertVal寻找插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while()时,说明已找到位置
            arr[insertIndex + 1] = insertVal;
        }
    }
}
