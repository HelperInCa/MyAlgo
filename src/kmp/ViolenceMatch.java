package kmp;

/**
 * @Author HuQing
 * @Date 10/20/20
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    /**
     * 如果用暴力匹配的思路，并假设现在 str1 匹配到 i 位置，子串 str2 匹配到 j 位置，则有:
     * 1) 如果当前字符匹配成功(即str1[i]==str2[j])，则i++，j++，继续匹配下一个字符
     * 2) 如果失配(即str1[i]!=str2[j])，令i = i-j+1，j = 0。相当于每次匹配失败时，i回溯，j被置为0。
     * 3) 用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间
     *
     * @param str1
     * @param str2
     * @return 匹配到的初始索引值, 未匹配到返回 -1
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int i = 0, j = 0;
        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == s2.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
