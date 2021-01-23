package tree;

public class ArrayBinaryTreeDemo {

}

/*
顺序存储二叉树特点:
1. 通常是完全二叉树
2. 第n个元素的左子节点为 2 * n + 1
3. 第n个元素的右子节点为 2 * n + 2
4. 第 n 个元素的父节点为 (n-1) / 2
n : 表示二叉树中的第几个元素(按 0 开始编号)
 */
// 顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr;
    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 2);
        }

    }
}