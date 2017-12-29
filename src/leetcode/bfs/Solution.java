package leetcode.bfs;

import java.util.*;

/**
 * Created by tangmh on 17/12/23.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 9.1
//        System.out.println(tc.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog","lot","log","cog")));
        // 9.2
        System.out.println(tc.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
     * <p>
     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * For example,
     * <p>
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * <p>
     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * UPDATE (2017/1/20):
     * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0) return 0;
        // 广度优先搜素
        // begin只要有可以变换的一步,queue
        // hit - hot - dot,lot - dog, log - cog
        // visited 辅助哈希表

        List<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int level = 1, n = 1;

        while (!queue.isEmpty()) {
            String vn = queue.get(0);
            queue.remove(0);
            n--;

            for (String vw :
                    wordList) {
                if (isTransform(vw, vn)) {
                    if (vw.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.contains(vw)) {
                        queue.add(vw);
                        visited.add(vw);
                    }
                }
            }
            if (n == 0) {
                n = queue.size();
                level++;
            }
        }
        return 0;
    }

    private boolean isTransform(String a, String b) {
        if (a.length() != b.length()) return false;
        int n = a.length();
        boolean sign = false;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (!sign) sign = true;
                else return false;
            }
        }
        return sign;
    }

    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
     * <p>
     * Only one letter can be changed at a time
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * For example,
     * <p>
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     * Note:
     * Return an empty list if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * UPDATE (2017/1/20):
     * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // TODO: 17/12/29 一直TLE  好烦好烦好烦好烦  先commit了 之后再说吧啊啊啊啊啊
        List<List<String>> res = new ArrayList<>();
        if (beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0) return res;

        List<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Set<String>> pre = new HashMap<>();

        queue.add(beginWord);
        visited.add(beginWord);
        int level = 1, n = 1;
        boolean end = false;

        while (!queue.isEmpty()) {
            String vn = queue.get(0);
            queue.remove(0);
            n--;

            for (String vw :
                    wordList) {
                if (isTransform(vw, vn)) {
                    if (!visited.contains(vw)) {
                        queue.add(vw);
                        visited.add(vw);
                        if (!pre.containsKey(vw)) pre.put(vw, new HashSet<>());
                        pre.get(vw).add(vn);
                    } else if (queue.contains(vw) && queue.indexOf(vw) >= n) {
                        // vw是同层次已访问过的节点(N对1的情况)
                        if (pre.containsKey(vw)) pre.get(vw).add(vn);
                        else System.err.println("err");
                    }
                    if (vw.equals(endWord)) end = true;
                }
            }

//            比上面那段性能还差 = =
//                for (int i = 0; i < vn.length(); i++) {
//                StringBuilder temp = new StringBuilder(vn);
//                for (char c = 'a'; c <= 'z'; c++) {
//                    temp.setCharAt(i,c);
//                    String vw = temp.toString();
//                    if (wordList.contains(vw)) {
//                        if (!visited.contains(vw)) {
//                            queue.add(vw);
//                            visited.add(vw);
//                            if (!pre.containsKey(vw)) pre.put(vw, new HashSet<>());
//                            pre.get(vw).add(vn);
//                        } else if (queue.contains(vw) && queue.indexOf(vw) >= n) {
//                            // vw是同层次已访问过的节点(N对1的情况)
//                            if (pre.containsKey(vw)) pre.get(vw).add(vn);
//                            else System.err.println("err");
//                        }
//                        if (vw.equals(endWord)) end = true;
//                    }
//                }
//            }

            if (n == 0) {
                if (end) break;
                n = queue.size();
                level++;
            }
        }
        if (!end) return res;
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs_result(pre, path, res);
        return res;
    }

    private void dfs_result(Map<String, Set<String>> pre, List<String> path, List<List<String>> res) {
        String word = path.get(0);
        if (!pre.containsKey(word)) res.add(new LinkedList<>(path));
        else {
            for (String preword :
                    pre.get(word)) {
                path.add(0, preword);
                dfs_result(pre, path, res);
                path.remove(0);
            }
        }
    }


}
