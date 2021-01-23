package kmp;

import java.util.Arrays;

/**
 * @Author HuQing
 * @Date 11/21/20
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD"); //[0, 0, 0, 0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15
    }

    /**
     * kmp 搜索算法
     * @param str1 目标字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return 没匹配到返回 -1, 否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 有时候，字符串头部和尾部会有重复。比如，”ABCDAB”之中有两个”AB”，那么 它的”部分匹配值”就是 2(”AB”的长度)。
     * 搜索词移动的时候，第一个”AB”向后移动 4 位(字符串长度- 部分匹配值)，就可以来到第二个”AB”的位置。
     *
     * @param dest 目标字符串
     * @return 部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;// 字符串长度为一, 部分匹配值为 0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当 dest.charAt(i) != dest.charAt(j), 要从 next[j - 1]获取新的 j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 当dest.charAt(i) == dest.charAt(j), 部分匹配值加 1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
