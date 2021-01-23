package divideandconquer;

/**
 * @Author HuQing
 * @Date 8/31/20
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, 'a', 'b', 'c');
    }
    public static void hanoi(int num, char left, char mid, char right) {
        if (num == 1) {
            System.out.println("第1个盘从" + left + " -> " + right);
        } else {
            hanoi(num - 1, left, right, mid);
            System.out.println("第" + num + "个盘从" + left + " -> " + right);
            hanoi(num - 1, mid, left, right);
        }
    }

}
