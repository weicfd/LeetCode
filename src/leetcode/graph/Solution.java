package leetcode.graph;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     * <p>
     * <p>
     * OJ's undirected graph serialization:
     * Nodes are labeled uniquely.
     * <p>
     * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
     * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
     * <p>
     * The graph has a total of three nodes, and therefore contains three parts as separated by #.
     * <p>
     * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
     * Second node is labeled as 1. Connect node 1 to node 2.
     * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
     * Visually, the graph looks like the following:
     * <p>
     * 1
     * / \
     * /   \
     * 0 --- 2
     * / \
     * \_/
     *
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//        真tm变态，居然不是有向图
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return cloneNode(map, node);

//        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
//        return cloneNode(map, node);
//        return bfsNodes(node);
    }

    //    private UndirectedGraphNode bfsNodes(UndirectedGraphNode node) {
//        if (node == null) return null;
//        Map<Integer, UndirectedGraphNode> nVisited = new HashMap<>();
//        List<UndirectedGraphNode> mVisit = new ArrayList<>(), mNext = new ArrayList<>();
//
//        mVisit.add(node);
//        while (!mVisit.isEmpty()) {
//            for (UndirectedGraphNode mvisit :
//                    mVisit) {
//                nVisited.put(mvisit.label, new UndirectedGraphNode(mvisit.label));
//                for (UndirectedGraphNode mneighbor :
//                        mvisit.neighbors) {
//                    if (mNext.contains(mneighbor)) mNext.remove(mneighbor);
//                    if (nVisited.containsKey(mneighbor.label)) {
//                        if (mvisit.label == mneighbor.label)
//                            nVisited.get(mneighbor.label).neighbors.add(nVisited.get(mvisit.label));
//                        else {
//                            nVisited.get(mneighbor.label).neighbors.add(nVisited.get(mvisit.label));
//                            nVisited.get(mvisit.label).neighbors.add(nVisited.get(mneighbor.label));
//                        }
//                    } else {
//                        mNext.add(mneighbor);
//                    }
//                }
//            }
//            mVisit = mNext;
//            mNext = new ArrayList<>();
//        }
//        return nVisited.get(node.label);
//    }
//
    private UndirectedGraphNode cloneNode(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for (UndirectedGraphNode neighbor :
                node.neighbors) {
            copy.neighbors.add(cloneNode(map, neighbor));
        }
        return copy;
    }

    public static void main(String[] args) {
        Solution tc = new Solution();
        // 133
//        {0,1,2#1,2#2,2}.
        UndirectedGraphNode n0 = new UndirectedGraphNode(0), n1 = new UndirectedGraphNode(1), n2 = new UndirectedGraphNode(2);
        n0.neighbors.add(n1);
        n0.neighbors.add(n2);
        n1.neighbors.add(n2);
        n2.neighbors.add(n2);

        tc.cloneGraph(n0);
    }
}
