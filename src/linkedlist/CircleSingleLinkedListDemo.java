package linkedlist;

// Josephus problem
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        return;
    }
}

/**
 * Boy 表示一个节点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

class CircleSingleLinkedList {
    // 创建第一个节点
    private Boy first;

    // 输入要添加的节点个数
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("num is wrong");
            return;
        }
        // 辅助指针
        Boy curBoy = null;

        for (int i = 0; i < nums; i++) {
            Boy boy = new Boy(i);
            // 第一个自己成环
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 显示所有节点
     */
    public void show() {
        if (first == null) {
            System.out.println("empty");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("Number %d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 计算出圈顺序
     * @param startNo  从第几个小孩开始报数
     * @param countNum 数几下
     * @param nums     最初有多少小孩在圈中
     */
    public void count(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("wrong");
            return;
        }
        Boy helper = first;
        // helper应指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 报数前,先让first helper 移动 startNo - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 报数时, first, helper 同时移动 countNum - 1次, 然后出圈, 直到只剩一个
        while (true) {
            if (helper == first) {
                break;
            }

            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后小孩编号 %d\n", first.getNo());
    }
}

