package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author HuQing
 * @Date 11/21/20
 * 存在如下广播台, 以及对应信号可以覆盖的地区. 如何选择最少的广播台使所有地区都可以收到信号.
 * 广播台             覆盖地区
 * k1               北京 上海 天津
 * k2               广州 北京 深圳
 * k3               成都 上海 杭州
 * k4               上海 天津
 * k5               杭州 大连
 */
public class Greedy {
    public static void main(String[] args) {
        // 广播台 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 临时集合. 在遍历过程中, 存放电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // maxKey: 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        while (allAreas.size() != 0) {// allAreas != 0: 未覆盖到所有地区
            String maxKey = null;

            // 遍历 braadcasts, 找到 key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();

                // 返回当前这个 key 能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);

                // tempSet 保留和 allAreas 的交集
                tempSet.retainAll(allAreas);

                // 如果当前集合包含的未覆盖地区数量大于 maxKey 指向的就需要更新 maxKey.
                // 此处体现 贪心算法
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            // 如果 maxKey 不为 null , 则会加入到 selects
            if (maxKey != null) {
                selects.add(maxKey);
                //从 allAreas 去掉 maxKey 指向的广播电台覆盖的地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("结果是" + selects);//[K1,K2,K3,K5]
    }
}
