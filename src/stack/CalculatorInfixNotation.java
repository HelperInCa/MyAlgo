package stack;

public class CalculatorInfixNotation {
    public static void main(String[] args) {
        String expression = "7+2*3-1";
        // 一个栈存数, 一个栈存符号
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //用于扫描
        int index = 0;

        int num1, num2, oper, res = 0;

        // 每次扫描到的char保存到ch
        char ch = ' ';
        // 用于拼接多位数
        String keepNum = "";
        // 扫描expression
        while (true) {
            //依次得到 expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 识别ch是什么, 然后处理
            if (operStack.oper(ch)) {
                if (!operStack.empty()) {
                    /*
                    如果当前的操作符的优先级小于或者等于栈中的操作符, 从数栈中pop出两个数,
                    再从符号栈中pop一个符号，运算，将结果入数栈，然后将当前的符号入符号栈.
                     */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空 入符号栈
                    operStack.push(ch);
                }
            } else {
                /*
                如果是数，入数栈
                (多位数时, 向 expression 的表达式的 index 后看一位,若是数就继续扫描，
                如果是符号就入栈. 因此要定义一个变量用于拼接)
                 */
                keepNum += ch;
                // 如果ch已经是expression最后一位, 入数栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.oper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        // 清空keepNum
                        keepNum = "";
                    }
                }
            }
            // index + 1, 判断是否扫到最后
            index++;
            if (index == expression.length()) {
                break;
            }
        }
        // 扫描完毕, 从数栈和符号栈中pop出相应的,并计算
        while (true) {
            // 如果符号栈为空, 则已算到最后结果, 数栈中只有一个数字--结果
            if (operStack.empty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("%s = %d", expression, res2);
    }
}

/**
 * 使用数组的栈
 * 需要拓展功能: 判断是否为运算符, 运算符优先级, 计算规则
 */
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
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
     * @param value 入栈
     */
    public void push(int value) {
        if (full()) {
            throw new RuntimeException("full");
        }
        top++;
        stack[top] = value;
    }

    /**
     * @return 出栈
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
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * @param val 输入的字符
     * @return 是否为运算符
     */
    public boolean oper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * @param oper 输入的运算符
     * @return 优先级. 数字越大越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * @param num1 第一个数
     * @param num2 第二个数
     * @param oper 运算符
     * @return 计算结果
     */
    public int cal(int num1, int num2, int oper) {
        // res 存放结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    public int peek() {
        return stack[top];
    }
}
