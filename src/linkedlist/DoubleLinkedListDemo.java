package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        return;
    }
}

// HeroNode2, 与单链表HeroNode区分
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("empty");
            return;
        }

        HeroNode2 temp = head.next;
        while (true) {
            if (temp.next == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加到最后
    public void add(HeroNode2 heroNode) {
        // 因为head不能动, 需要一个辅助节点
        HeroNode2 temp = head;
        // 遍历, 找到最后一个
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 修改
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("empty");
            return;
        }

        // 找到需要修改的节点
        HeroNode2 temp = head.next;
        boolean flag = false;// 表示是否找到
        while (true) {
            if (temp == null) {
                break;// 已遍历完
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("no %d\n", newHeroNode.no);
        }

    }

    // 删除
    public void del(int no) {
        // 判断当前链表是否为空
        if (head.next == null) {// 空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; // 标志是否找到待删除节点
        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            temp = temp.next; //temp 后移，遍历
        }

        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre; }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}
