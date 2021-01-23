package stack;

public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean full() {
        return top == maxSize - 1;
    }

    public boolean empty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (full()) {
            throw new RuntimeException("full");
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (empty()) {
            throw new RuntimeException("empty");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (empty()) {
            System.out.println("empty");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
