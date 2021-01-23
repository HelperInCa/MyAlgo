package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // leftType == 0 : 左子树; 1 : 前驱结点
    // rightType == 0 : 右子树; 1 : 后继结点
    private int leftType;
    private int rightType;

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序中序后序遍历
    public void preOrder() {
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void inOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    public void postOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 待查找的Node
     * @return 如果找到返回该Node, 没有则返回null
     */
    public HeroNode preOrderSearch(int no) {
        // 1. 判断当前结点是否为待查找的
        if (this.no == no) {
            return this;
        }
        // 2. 判断左子节点是否为空, 若不为空则递归前序查找, 若找到则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 3. 同样判断右子节点
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode inOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.inOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.inOrderSearch(no);
        }
        return resNode;
    }

    /* 递归删除结点
       规定: 如果删除的结点是叶子结点, 则删除该节点; 如果是非叶子结点, 则删除该子树
     */
    public void delNode(int no) {
        // 如果当前结点的左子节点不为空且左子节点就是待删除的结点, 则将其置空并返回
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 同样的, 处理右子节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }

    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 中序线索化结点方法
     *
     * @param node 当前待线索化的结点
     */
    public void threadedNodes(HeroNode node) {
        // 不能线索化空结点
        if (node == null) {
            return;
        }

        // 线索化左子树
        threadedNodes(node.getLeft());

        // 线索化当前结点
        // 前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
   }

    // 前中后序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void inOrder() {
        if (this.root != null) {
            this.root.inOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 删除结点
    public void delNode(int no) {
        if (root != null) {
            // 判断该节点是否为待删除的结点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树");
        }
    }

}