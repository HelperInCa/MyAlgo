package binarysearch;

/**
 * @Description recursion & no-recursion
 * @Author
 * @Date 8/23/20 22:41
 * @Version 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index1 = binarySearchNoRecur(arr, 100);
        System.out.println("循环: " + index1);

        int index2 = binarySearchRecur(arr, 100, 0, arr.length - 1);
        System.out.println("递归: " + index2);
    }

    /**
     * 二分查找非递归实现
     * @param arr 待查找的数组, arr 是升序
     * @param target 待查找的数
     * @return 返回对应下标，-1 表示没有找到
     */
    public static int binarySearchNoRecur(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (right - left)/2 + left;
            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     *二分查找递归实现
     * @param arr 待查找的数组, arr 是升序
     * @param target 待查找的数
     * @param left 左下标(初始是 0)
     * @param right 右下标(初始是 array.length() - 1)
     * @return 返回对应下标，-1 表示没有找到
     */
    public static int binarySearchRecur(int[] arr, int target, int left, int right) {
        int mid = (right - left)/2 + left;
        if (arr[mid] == target) {
            return mid;
        }
        if (left >= right) {
            return -1;
        } else if (target > arr[mid]) {
            return binarySearchRecur(arr, target,mid + 1, right);
        } else if (target < arr[mid]){
            return binarySearchRecur(arr, target, left, mid - 1);
        }
        return -1;
    }
}
