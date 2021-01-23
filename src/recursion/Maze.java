package recursion;

/*
        迷宫:
        原地图
        1 1 1 1 1 1 1
        1 0 0 0 0 0 1
        1 0 0 0 0 0 1
        1 1 1 0 0 0 1
        1 0 0 0 0 0 1
        1 0 0 0 0 0 1
        1 0 0 0 0 0 1
        1 1 1 1 1 1 1

        走过的地图
        1 1 1 1 1 1 1
        1 2 0 0 0 0 1
        1 2 2 2 0 0 1
        1 1 1 2 0 0 1
        1 0 0 2 0 0 1
        1 0 0 2 0 0 1
        1 0 0 2 2 2 1
        1 1 1 1 1 1 1
 */
public class Maze {
    public static void main(String[] args) {
        // 创建二维数组, 模拟迷宫
        int[][] map = new int[8][7];
        /*
        1. i, j 表示出发点坐标
        2. 走到[6][5]表示找到通路
        3. 0: 没走过, 1: 墙, 2: 可以走, 3: 不通
        4. 约定: 下->右->上->左
         */
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("原地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        findWay(map, 1, 1);


        System.out.println("走过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param map 地图
     * @param i   第i行
     * @param j   第j列
     * @return 通路true, 不通false
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 下->右->上->左
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}

