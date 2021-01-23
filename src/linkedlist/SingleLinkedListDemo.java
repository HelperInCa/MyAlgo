package linkedlist;

// 要求: 水浒英雄排行榜管理完成对英雄人物的增删改查操作

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        return;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode [name=" + name + ", next=" + next + ", nickName=" + nickName + "]";
    }
}

class SingleLinkedList {
    // 头结点,不能动
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点(不考虑顺序)
    // 1. 找到最后一个节点
    // 2. 将它的next指向新节点
    public void add(HeroNode heroNode) {
        // 因为head不能动, 需要一个辅助节点
        HeroNode temp = head;
        // 遍历, 找到最后一个
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    // 添加(考虑顺序, 如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        // temp应位于添加位置的前一个节点
        HeroNode temp = head;
        boolean flag = false;// 添加的人是否存在
        while (true) {
            if (temp.next == null) {// temp已在最后
                break;
            }
            if (temp.next.no == heroNode.no) {// heroNode已存在
                flag = true;
                break;
            } else if (temp.next.no > heroNode.no) {// 位置找到
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("%d exists\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("empty");
            return;
        }

        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 修改节点信息
    // 根据newHeroNode.no改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("empty");
            return;
        }

        // 找到需要修改的节点
        HeroNode temp = head.next;
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
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("no %d\n", newHeroNode.no);
        }

    }

    // 删除节点
    // head 不能动，需要一个 temp 辅助节点找到待删除节点的前一个节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点
        while (true) {
            if (temp.next == null) { //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到的待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            temp = temp.next; //temp 后移，遍历
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}
