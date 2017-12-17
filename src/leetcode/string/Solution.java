package leetcode.string;

import java.util.*;

/**
 * Created by tangmh on 17/11/26.
 */
public class Solution {

    public static void main(String[] args) {
        Solution tc = new Solution();

        // 3.1
//        System.out.println(tc.isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(tc.isPalindrome("race a car"));

        // 3.2
//        System.out.println(tc.strStr("hello","ll"));
//        System.out.println(tc.strStr("a", "a"));

        // 3.3
//        System.out.println(tc.myAtoi("-3924x8fc"));
//        System.out.println(tc.myAtoi("+ 413"));
//        System.out.println(tc.myAtoi("++c"));
//        System.out.println(tc.myAtoi("2147483648"));

        // 3.4
//        System.out.println(tc.addBinary("110", "11111111"));

        // 3.5
//        System.out.println(tc.longestPalindrome("babad"));
//        System.out.println(tc.longestPalindrome("caba"));

        // 3.6
//        System.out.println(tc.mydp_isMatch("", ".*.*."));
//        System.out.println(tc.mydp_isMatch("aa", "aa"));
//        System.out.println(tc.mydp_isMatch("aaa", "ab*a"));
//        System.out.println(tc.mydp_isMatch("aa", "a*"));
//        System.out.println(tc.mydp_isMatch("aa", ".*"));
//        System.out.println(tc.mydp_isMatch("ab", ".*"));
//        System.out.println(tc.mydp_isMatch("aab", "c*a*b"));

        // 3.7

//        System.out.println(tc.isMatch_wildCard("aa", "a"));
//        System.out.println(tc.isMatch_wildCard("aa", "aa"));
//        System.out.println(tc.isMatch_wildCard("aaa", "aa"));
//        System.out.println(tc.isMatch_wildCard("aa", "*"));
//        System.out.println(tc.isMatch_wildCard("aa", "a*"));
//        System.out.println(tc.isMatch_wildCard("ab", "?*"));
//        System.out.println(tc.isMatch_wildCard("aab", "c*a*b"));

        // 3.8
//        System.out.println(tc.longestCommonPrefix(new String[]{"", ""}));
//        System.out.println(tc.longestCommonPrefix(new String[]{"sf", ""}));
//        System.out.println(tc.longestCommonPrefix(new String[]{"sddd", "s"}));
//        System.out.println(tc.longestCommonPrefix(new String[]{"s", "s"}));

        // 3.9
//        "0" => true
//        " 0.1 " => true
//        "abc" => false
//        "1 a" => false
//        "2e10" => true
//        System.out.println(tc.isNumber("0"));
//        System.out.println(tc.isNumber(" 0.1 "));
//        System.out.println(tc.isNumber("abc"));
//        System.out.println(tc.isNumber("1 a"));
//        System.out.println(tc.isNumber("2e10"));

        // 3.10 num to roman
//        System.out.println(tc.intToRoman(4));

        // 3.11 roman to num
//        System.out.println(tc.romanToInt("MMMCMLXXVIII"));

        // 3.12
//        System.out.println(tc.countAndSay(6));

        // 3.13
//        System.out.println(tc.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

        // 3.14
//        System.out.println(tc.simplifyPath("/home/"));
//        System.out.println(tc.simplifyPath("/a/./b/../../c/"));
//        System.out.println(tc.simplifyPath("/../"));
//        System.out.println(tc.simplifyPath("/home//foo/"));

        // 3.15
//        System.out.println(tc.lengthOfLastWord(" "));
    }

    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     * <p>
     * Note:
     * Have you consider that the string might be empty? This is a good question to ask during an interview.
     * <p>
     * For the purpose of this problem, we define empty string as valid palindrome.
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z]", "").toLowerCase();
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implement strStr().
     * <p>
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * <p>
     * Example 1:
     * <p>
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     * <p>
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * <p>
     * // 思路2 indexOf
     * // 思路3 kmp算法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0 && needle.length() == 0) return 0;
        if (needle.length() < 1) return -1;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 1;
                for (; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) break;
                }
                if (j == needle.length()) return i;
            }
        }
        return -1;
    }

    /**
     * Implement atoi to convert a string to an integer.
     * <p>
     * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
     * <p>
     * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
     * <p>
     * Requirements for atoi:
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
     * <p>
     * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
     * <p>
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     * <p>
     * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        // 空字符串
        if (str.length() == 0) return 0;

        str = str.trim();
        // 符号
        int sign = 1, i = 0;
        if (str.charAt(i) == '-') {
            i++;
            sign = -1;
        } else if (str.charAt(i) == '+') {
            i++;
        }

        long sum = 0;

        while (i < str.length()) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') break;
            sum = sum * 10 + c - '0';

            // 上下界
            if (sum * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sum * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }

        return (int) (sum * sign);

    }

    /**
     * Given two binary strings, return their sum (also a binary string).
     * <p>
     * For example,
     * a = "11"
     * b = "1"
     * Return "100".
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) return addBinary(b, a);

        int i = a.length() - 1, j = b.length() - 1;
        String res = "";
        int carry = 0;

        while (i >= 0) {
            int x = a.charAt(i) - '0', y;
            if (j >= 0) y = b.charAt(j) - '0';
            else y = 0;

            int c = x + y + carry;
            if (c > 1) {
                c = c % 2;
                carry = 1;
            } else {
                carry = 0;
            }
            res = c + res;

            i--;
            j--;
        }

        if (carry > 0) res = '1' + res;

        return res;
    }

    /**
     * Given a string S, find the longest palindromic substring in S.
     * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length(), maxlen = 1, start = 0;

        boolean[][] book = new boolean[n][n];

        for (int i = 0; i < n; i++) book[i][i] = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                book[i][i + 1] = true;
                if (maxlen < 2) {
                    maxlen = 2;
                    start = i;
                }
            }
        }

        for (int j = 2; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                if (s.charAt(i) == s.charAt(i + j) && book[i + 1][i + j - 1]) {
                    if (maxlen < j + 1) {
                        maxlen = j + 1;
                        start = i;
                    }
                    book[i][i + j] = true;
                }
            }
        }

        return s.substring(start, start + maxlen);
    }

    /**
     * Implement regular expression matching with support for '.' and '*'.
     * <p>
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // return s.matches(p);  it works...

        if (s.equals("") && p.equals("")) return true;
        else if (p.equals("")) return false;

        if (p.charAt(0) == '.') {
            if (p.length() >= 2 && p.charAt(1) == '*') {
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(2))) return true;
                }
                return false;
            } else {
                if (s.equals("")) return false;
                return isMatch(s.substring(1), p.substring(1));
            }
        } else { // 以匹配字符开头
            if (p.length() >= 2 && p.charAt(1) == '*') {
                if (s.equals("")) return isMatch(s, p.substring(2));
                for (int i = 0; i <= s.length(); i++) {
                    if (i < s.length() && s.charAt(i) != p.charAt(0)) return isMatch(s.substring(i), p.substring(2));
                    if (isMatch(s.substring(i), p.substring(2))) return true;
                }
                return false;
            } else {
                if (s.equals("")) return false;
                if (s.charAt(0) != p.charAt(0)) return false;
                else return isMatch(s.substring(1), p.substring(1));
            }
        }
    }

    boolean dp_isMatch(String s, String t) {
        // 以下是动归实现的实例代码
        if (s == null || t == null) {
            return false;
        }

        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j >= 2 && dp[0][j - 2] && t.charAt(j - 1) == '*';
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (t.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && match(s.charAt(i - 1), t.charAt(j - 1));

                } else {

                    boolean matchCase1 = j >= 2 && dp[i][j - 2];
                    boolean matchCase2 = j >= 2 && dp[i - 1][j] && match(s.charAt(i - 1), t.charAt(j - 2));
                    dp[i][j] = matchCase1 || matchCase2;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    boolean match(char c1, char c2) {
        if (c2 == '.') return true;
        return c1 == c2;
    }

    boolean mydp_isMatch(String s, String t) {
        if (s == null || t == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];

        dp[0][0] = true;

        for (int j = 1; j <= t.length(); j++) {
            dp[0][j] = (j > 1 && dp[0][j - 2] && t.charAt(j - 1) == '*');
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (j == 0) dp[i][j] = false;
                else if (t.charAt(j - 1) == '*') {
                    boolean b1 = (j > 1 && dp[i][j - 2]);
                    boolean b2 = (j > 1 && dp[i - 1][j] && match(s.charAt(i - 1), t.charAt(j - 2)));
                    dp[i][j] = b1 || b2;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] && match(s.charAt(i - 1), t.charAt(j - 1)));
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    /**
     * Implement wildcard pattern matching with support for '?' and '*'.
     * <p>
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_wildCard(String s, String p) {
        /**
         * 动归
         *
         * 基本情况:
         * 1. dp[0][0] = true
         * 2. dp[0][j] = dp[0][j-1] && t[j] = '*'
         * 3. dp[i][0] = false
         *
         * 一般情况:
         * 1. t[j]不是'*' dp[i][j] = dp[i-1][j-1] && match(s[i], t[j])
         * 2. t[j]是'*' dp[i][j] = dp[i-1][j] || dp[i][j-1]
         */
        if (s == null || p == null) return false;

        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        dp[0][0] = true;

        for (int j = 1; j <= pLen; j++) dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = (i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'));
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[sLen][pLen];
    }

    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";

        int m = strs.length;
        if (m == 0) return "";
        int n = strs[0].length();

        for (int i = 0; i < n; i++) {
            char c = strs[0].charAt(i);
            int j;
            for (j = 1; j < m; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != c) break;
            }
            if (j == m) prefix += c;
            else break;
        }

        return prefix;
    }

    /**
     * Validate if a given string is numeric.
     * <p>
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
     *
     * @param s
     * @return
     */
    public boolean isNumber_regex(String s) {
        // 本题可以使用正则表达式
        return s.matches("(\\s*)(\\+|\\-)?((\\d+\\.)|(\\d*\\.?\\d+))((e|E)(\\+|\\-)?\\d+)?(\\s*)");
    }

    public boolean isNumber(String s) {
        // 有限自动机
        final int INVALID = 0, SPACE = 1, DIGIT = 2, SIGN = 3, DOT = 4, EXP = 5;
        int[][] stateTable = {
                {-1, 0, 1, 3, 2, -1},
                {-1, 8, 1, -1, 4, 5},
                {-1, -1, 4, -1, -1, -1},
                {-1, -1, 1, -1, 2, -1},
                {-1, 8, 4, -1, -1, 5},
                {-1, -1, 7, 6, -1, -1},
                {-1, -1, 7, -1, -1, -1},
                {-1, 8, 7, -1, -1, -1},
                {-1, 8, -1, -1, -1, -1}
        };
        int state = 0;

        for (char c :
                s.toCharArray()) {
            int type;
            if (c == ' ') {
                type = SPACE;
            } else if (c >= '0' && c <= '9') {
                type = DIGIT;
            } else if (c == '+' || c == '-') {
                type = SIGN;
            } else if (c == '.') {
                type = DOT;
            } else if (c == 'E' || c == 'e') {
                type = EXP;
            } else {
                type = INVALID;
            }
            state = stateTable[state][type];
            if (state == -1) return false;
        }

        return state == 1 || state == 4 || state == 7 || state == 8;
    }

    /**
     * Given an integer, convert it to a roman numeral.
     * <p>
     * Input is guaranteed to be within the range from 1 to 3999.
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String[] symbol = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] val = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        String roman = "";

        for (int i = 0; i < 13; i++) {
            int count = num / val[i];
            num %= val[i];
            for (int j = 0; j < count; j++) {
                roman += symbol[i];
            }
        }

        return roman;
    }

    /**
     * Given a roman numeral, convert it to an integer.
     * <p>
     * Input is guaranteed to be within the range from 1 to 3999.
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            int a = map(s.charAt(i)), b = i == s.length() - 1 ? 0 : map(s.charAt(i + 1));
            if (a < b) {
                num += b - a;
                i++;
            } else {
                num += a;
            }
        }
        return num;
    }

    public int map(char c) {
        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    /**
     * The count-and-say sequence is the sequence of integers with the first five terms as following:
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth term of the count-and-say sequence.
     * <p>
     * Note: Each term of the sequence of integers will be represented as a string.
     * <p>
     * Example 1:
     * <p>
     * Input: 1
     * Output: "1"
     * Example 2:
     * <p>
     * Input: 4
     * Output: "1211"
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        // 使用string builder可以优化代码
        String s = "1";

        for (int i = 1; i < n; i++) {
            String s_new = "";
            int j = 0;
            while (j < s.length()) {
                int count = 1;
                while (j < s.length() - 1 && s.charAt(j) == s.charAt(j + 1)) {
                    j++;
                    count++;
                }
                s_new += Integer.toString(count) + s.charAt(j);
                j++;
            }
            s = s_new;
        }
        return s;
    }

    /**
     * Given an array of strings, group anagrams together.
     * <p>
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Return:
     * <p>
     * [
     * ["ate", "eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note: All inputs will be in lower-case.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 注意: string不能直接sort 要转char array
        // string to char array : s.toCharArray()
        // char array to string : !!! String.valueOf(keyArr)!!!
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s :
                strs) {
            char[] keyArr = s.toCharArray();
            Arrays.sort(keyArr);
            String key = String.valueOf(keyArr);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Given an absolute path for a file (Unix-style), simplify it.
     * <p>
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     * <p>
     * Corner Cases:
     * Did you consider the case where path = "/../"?
     * In this case, you should return "/".
     * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     * In this case, you should ignore redundant slashes and return "/home/foo".
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String token :
                tokens) {
            if (token.equals("") || token.equals(".")) {
                continue;
            } else if (token.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(token);
            }
        }
        if (stack.empty()) return "/";

        StringBuilder res = new StringBuilder();
        while (!stack.empty()) {
            res.insert(0, stack.pop());
            res.insert(0, '/');
        }
        return res.toString();
    }

    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
     * <p>
     * If the last word does not exist, return 0.
     * <p>
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * <p>
     * Example:
     * <p>
     * Input: "Hello World"
     * Output: 5
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int i = s.lastIndexOf(" ");
        return s.substring(i + 1).length();
    }
}
