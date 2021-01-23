package stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPN {
    public static void main(String[] args) {
        /* (30+4)×5-6 =>30 4 + 5 × 6 - => 164
            为了方便, 用空格隔开数字 符号
         */
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println(list + " = " + res);
    }

    /**
     * 将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
     *
     * @param suffixExpression 逆波兰表达式
     * @return ArrayList
     */
    public static List<String> getListString(String suffixExpression) {
        // 分割suffixExpression
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /* 1)从左至右扫描，将 3 和 4 压入堆栈;
       2)遇到+运算符，因此弹出 4 和 3(4 为栈顶元素，3 为次顶元素)，计算出 3+4 的值，得 7，再将 7 入栈;
       3)将 5 入栈;
       4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈;
       5)将 6 入栈;
       6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */

    /**
     * 对逆波兰表达式运算
     * @param ls ArrayList
     * @return 结果
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            // 正则来取多位数
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                // pop出两个数, 运算, 入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
