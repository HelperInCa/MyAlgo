package avl;

public class AVLTreeDemo {
}

class AVLTree {
    private Node root;

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除node的3种情况:
     * 1. 叶子结点
     * 2. 有一个子树
     * 3. 有两个子树
     *
     * @param value 待删除的结点值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 找到待删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 二叉树只有一个结点, 只有根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 找到targetNode父节点
            Node parent = searchParent(value);
            // 1. 待删除的是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // targetNode是左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { // 右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 2. 删除有两个子树的结点
                targetNode.value = delRightTreeMin(targetNode.right);
            } else { // 3. 删除只有一个子树的结点
                // target有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // target是parent左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { // target是parent右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // target有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 1. 返回以node为根节点的二叉排序树的最小结点值
     * 2. 删除这个最小结点
     *
     * @param node 当作根节点
     * @return 以node为根节点的二叉排序树的最小结￿￿点值
     */
    public int delRightTreeMin(Node node) {
        Node res = node;
        // 向左查找
        while (res.left != null) {
            res = res.left;
        }
        delNode(res.value);
        return res.value;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.inOrder();
        }
    }


}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 返回以该结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height());
    }

    // 返回左子树高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 左旋转
    private void leftRotate() {
        // 创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树设置成新的结点
        left = newNode;
    }

    // 右旋转
    private void rightRotate() {
        // 创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的右子树设置成当前结点的右子树
        newNode.right = right;
        //把新的结点的左子树设置成当前结点的左子树的右子树
        newNode.left = left.right;
        //把当前结点的值替换成左子结点的值
        value = left.value;
        //把当前结点的左子树设置成当前结点左子树的左子树
        left = left.left;
        //把当前结点的右子树设置成新的结点
        right = newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 如果 (右子树高度 - 左子树高度) > 1, 左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果右子树的左子树高度大于右子树的右子树高度, 则先对右子树右旋转
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightHeight();
            }
            leftRotate();
            // 避免旋转两次
            return;
        }

        // 如果 (左子树高度 - 右子树高度) > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果左子树的右子树高度大于左子树的左子树高度, 则先对左子树左旋转
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftHeight();
            }
            rightRotate();
        }
    }

    // 中序遍历
    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    /**
     * 查找删除的结点
     *
     * @param value 希望删除的结点值
     * @return 如果找到则返回该节点, 否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父节点
     *
     * @param value 待删除的结点值
     * @return 待删除的结点的父节点, 若没有则返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}
