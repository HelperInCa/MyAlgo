package sort;
// 分治, 平均O(n*logn), 最坏O(n^2)
public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        // 比pivot小的放左边, 比pivot大的放右边
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            // l >= r 说明pivot两边已排好
            if ( l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 避免有值和pivot相同 进入死循环
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 防止栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
