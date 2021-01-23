package queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        return; // test
    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    // 创建队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1; // front指向头部前一个
        rear = -1; // rear指向队尾
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean full() {
        return rear == maxSize - 1;
    }

    // 判断队列是否空
    public boolean empty() {
        return rear == front;
    }

    // 加
    public void addQueue(int n) {
        // 是否满
        if (full()) {
            System.out.println("queue is full");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    // 取
    public int getQueue() {
        // 是否空
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }
        front++;
        return arr[front];
    }

    // 显示所有数据
    public void showQueue() {
        if (empty()) {
            throw new RuntimeException("no data");
        }
        for (int i : arr) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 显示头数据
    public int headQueue() {
        if (empty()) {
            throw new RuntimeException("empty");
        }
        return arr[front + 1];
    }
}

class CircleArrayQueue {
    private int maxSize;
    private int front; // front 就指向队列的第一个元素
    private int rear; // rear 指向队列的最后一个元素的后一个位置. 空出一个空间
    private int[] arr;

    // 创建队列
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满(!)
    public boolean full() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否空
    public boolean empty() {
        return rear == front;
    }

    // 加
    public void addQueue(int n) {
        // 是否满
        if (full()) {
            System.out.println("queue is full");
            return;
        }
        // 将数据加入
        arr[rear] = n;
        // 后移rear
        rear = (rear + 1) % maxSize;
    }

    // 取
    public int getQueue() {
        // 是否空
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }
        /*
         * 1. 先把front对应的值保留到一个临时变量 2. 将front后移,取模 3. 返回临时保存变量
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示所有数据
    public void showQueue() {
        if (empty()) {
            throw new RuntimeException("no data");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 环形队列有效容量
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头数据
    public int headQueue() {
        if (empty()) {
            throw new RuntimeException("empty");
        }
        return arr[front];
    }
}