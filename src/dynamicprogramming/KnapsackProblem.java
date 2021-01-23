package dynamicprogramming;

/**
 * @Author HuQing
 * @Date 9/14/20
 * 容量为 4 的背包. 有如下物品,要求装入的物品不重复,求最大总价值
 * 物品 | 重量 | 价格
 * 吉他 |  1  | 1500
 * 音箱 |  4  | 3000
 * 电脑 |  3  | 2000
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 重量
        int[] w = {1, 4, 3};
        // 价值
        int[] val = {1500, 3000, 2000};
        // 背包容量
        int m = 4;
        // 物品个数
        int n = val.length;

        // v[i][j] : 前 i 个物品能够装入容量为 j 的背包中最大价值
        int[][] v = new int[n + 1][m + 1];
        // 记录放入商品的情况
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行和第一列, 在本例中，可以不处理，因为默认都是 0

        // DP
        for (int i = 1; i < v.length; i++) { // i 从 1 开始: 不管第一行
            for (int j = 1; j < v[0].length; j++) { // j 从 1 开始: 不管第一列
                // 如果待加入的商品大于当前背包容量
                if (w[i - 1] > j) {
                    v[i][j]=v[i-1][j];
                } else {
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    // 记录商品放到背包的情况, 不直接使用上面公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 记录
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
