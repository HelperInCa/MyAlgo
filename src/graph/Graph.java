package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Description Graph
 * @Author
 * @Date 8/23/20 15:18
 * @Version 1.0
 */
public class Graph {
    // 顶点集合
    private ArrayList<String> vertexList;
    // 邻接矩阵
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    public static void main(String[] args) {

    }

    /**
     * 深度优先遍历
     *
     * @param isVisited
     * @param i         下标, 第一次是 0
     */
    private void dfs(boolean[] isVisited, int i) {
        // 访问该节点, 打印
        System.out.print(getValueByIndex(i) + " -> ");
        // 将节点置为已访问
        isVisited[i] = true;
        // 查找节点 i 的第一个领接节点 w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 遍历, dfs 回溯
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个节点广度优先遍历
     *
     * @param isVisited
     * @param i         下标
     */
    private void bfs(boolean[] isVisited, int i) {
        // 头结点的下标
        int u;
        // 邻接结点的下标
        int w;
        // 记录访问顺序的队列
        LinkedList queue = new LinkedList();
        // 访问节点, 打印并置为已访问
        System.out.print(getValueByIndex(i) + " => ");
        isVisited[i] = true;
        // 加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列头结点
            u = (int) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " => ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                // 以 u 为前驱, 找 w 后面的邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * @param index 结点下标
     * @return 如果存在则返回第一个邻接结点下标, 否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param v1 当前结点
     * @param v2 前一个邻接结点下标
     * @return 后一个邻接结点下标
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * @return 返回节点个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges
        ) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @return v1 - v2 权值
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个点的下标(如"A" -> 0)
     * @param v2     第一个点的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * @param i 下标
     * @return 下标 i 对应的数据 (如: 0 -> "A')
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

}
