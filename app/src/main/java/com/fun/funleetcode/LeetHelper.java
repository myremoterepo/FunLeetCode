package com.fun.funleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by fanzf on 2016/10/27. https://leetcode.com/problems/longest-absolute-file-path/
 **/

public class LeetHelper {

    /**
     * dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext
     */
    public static int lengthLongestPath(String input) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (!input.contains("\n") && input.contains(".")) {
            result = input.length();
        } else {
            for (String item : input.split("\n")) {
                int level = item.lastIndexOf("\t") + 1;
                int len = item.substring(level).length();
                if (item.contains(".")) {
                    result = Math.max(result, map.get(level) + len);
                } else {
                    if (null == map.get(level)) {
                        map.put(level, 0);
                    }
                    map.put(level + 1, map.get(level) + len + 1);
                }
            }
        }

        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length < 1) {
            return "";
        }
        String prefix = strs[0];

        for (String str : strs) {
            int len = Math.min(prefix.length(), str.length());
            for (int i = len; i > 0; i--) {
                if (prefix.substring(0, i).equals(str.substring(0, i))) {
                    len = i;
                    break;
                }
                if (i == 1) {
                    len = 0;
                }
            }
            prefix = prefix.substring(0, len);
        }
        return prefix;
    }


    /**
     * a + b + c + d = target
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i > 2; i--) {
            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
                ArrayList<ArrayList<Integer>> curRes = threeSum(nums, i - 1, target - nums[i]);
                for (int j = 0; j < curRes.size(); j++) {
                    curRes.get(j).add(nums[i]);
                }
                res.addAll(curRes);
            }
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> threeSum(int[] nums, int end, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int i = end; i > 1; i--) {
            if (i == end || nums[i] != nums[i + 1]) {
                ArrayList<ArrayList<Integer>> curRes = twoSum(nums, i - 1, target - nums[i]);
                for (int j = 0; j < curRes.size(); j++) {
                    curRes.get(j).add(nums[i]);
                }
                res.addAll(curRes);
            }
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> twoSum(int[] nums, int end, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int l = 0;
        int r = end;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                ArrayList<Integer> item = new ArrayList<Integer>();
                item.add(nums[l]);
                item.add(nums[r]);
                res.add(item);
                l++;
                r--;
                while (l < r && nums[l] == nums[l - 1])
                    l++;
                while (l < r && nums[r] == nums[r + 1])
                    r--;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }

    public String reverseString(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] tmp = s.toCharArray();
        for (int i = 0; i < tmp.length / 2; i++) {
            char v = tmp[i];
            tmp[i] = tmp[tmp.length - i];
            tmp[tmp.length - i] = v;
        }
        s = String.valueOf(tmp);
        return s;

        /*
        * another way
        *
        * public String reverseString(String s) {
            char[] chars = new char[s.length()];
            int index = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                chars[index++] = s.charAt(i);
            }
            return new String(chars);
        }
        * */
    }

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        String threePar = "Fizz";
        String fivePar = "Buzz";
        for (int i = 1; i <= n; i++) {
            String item = "";
            if (i < 3 || i == 4 || (i % 3 != 0 && i % 5 != 0)) {
                item = "" + i;
            } else {
                if (i % 3 == 0) {
                    item = threePar;
                    if (i % 5 == 0) {
                        item = item + fivePar;
                    }
                } else {
                    item = fivePar;
                }
            }
            res.add(item);
        }

        return res;
    }

    /**
     * 题目理解起来有点坑 有个条件容易被忽略：假设两人一共取了m次，则m次正好取了n个。
     */
    public boolean canWinNim(int n) {
        for (int i = 1; i < 4; i++) {
            int m = n / (2 * i);
            if ((2 * m + 1) * i == n) {
                if (2 * m * i < n && (2 * m + 1) * i >= n) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    /*public boolean canWinNim(int n) {
        if(n%4 == 0) return false;
        else return true;
    }*/


    /*public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length; i = i + 2) {
            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return -1;
    }*/

    /**
     * 用异或
     */
    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] ^ nums[i];
        }

        return nums[0];
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int addDigits(int num) {
        if (num == 0) return 0;
        return num % 9 == 0 ? 9 : num % 9;
    }

    public static char findTheDifference(String s, String t) {
        int charCodeS = 0, charCodeT = 0;
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) charCodeS += (int) s.charAt(i);
        for (int i = 0; i < t.length(); ++i) charCodeT += (int) t.charAt(i);
        // Return the difference between 2 strings as char
        return (char) (charCodeT - charCodeS);
    }


    public class Solution {
        public TreeNode invertTree(TreeNode root) {

            if (root != null) {
                TreeNode tmp;
                tmp = root.left;
                root.left = root.right;
                root.right = tmp;
                invertTree(root.left);
                invertTree(root.right);
            }

            return root;
        }
    }

    /*public static int[] moveZeroes(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length - size; i++) {
            int j = i + size;
            while (j < nums.length && nums[j] == 0) {
                size++;
                j++;
            }
            if (i + size < nums.length) {
                nums[i] = nums[i + size];
            }
        }
        for (int k = 0; k < size; k++) {
            nums[nums.length - k - 1] = 0;
        }
        return nums;
    }*/

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        int sum1 = 0;
        if (root == null || (root.left == null && root.right == null)) return 0;


        if (root.left != null && checkLeft(root.left)) {
            sum1 = sum1 + root.left.val + sumOfLeftLeaves(root.right);
        } else {
            sum1 = sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
        return sum1;
    }

    private boolean checkLeft(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        return false;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            String ii = ransomNote.substring(i, i + 1);
            int index = magazine.indexOf(ii);
            if (index == -1) {
                return false;
            } else {
                magazine = magazine.replaceFirst(ii, "");
            }
        }

        return true;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            } else {
                return false;
            }
        } else if (p == null && q == null) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {

        List<Integer> set = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (!set.contains(nums1[i])) {
                    set.add(nums1[i]);
                }
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                i++;
                j++;
            }
        }

        int[] res = new int[set.size()];
        for (int k = 0; k < set.size(); k++) {
            res[k] = set.get(k);
        }

        return res;

        /*List<Integer> res = new ArrayList<>();
        int[] nums;
        if (nums1.length > nums2.length) {
            nums = nums2;
        } else {
            nums = nums1;
            nums1 = nums2;
        }
        Arrays.sort(nums);
        Arrays.sort(nums1);
        for (int i = 0; i < nums.length; i++) {
            if (res.contains(nums[i])) {
                continue;
            }
            if (check(nums[i], nums1)) {
                Log.e("fan", "ddd.." + nums[i]);
                res.add(nums[i]);
            }
        }
        int[] dd = new int[res.size()];
        for (int k = 0; k < res.size(); k++) {
            dd[k] = res.get(k);
        }
        return dd;*/
    }

    private static boolean check(int a, int[] nums) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int cur = (end + start) / 2;
            if (cur <= start) {
                if (a == nums[start] || a == nums[end]) {
                    return true;
                } else {
                    return false;
                }
            }
            if (nums[cur] > a) {
                end = cur;
            } else if (nums[cur] < a) {
                start = cur;
            } else {
                return true;
            }
        }
        return false;
    }

    public int titleToNumber(String s) {
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            number += (s.charAt(i) - 64) * Math.pow(26, s.length() - 1 - i);
        }
        return number;
    }

    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i + 1);
            if (s.lastIndexOf(c) == s.indexOf(c)) {
                return i;
            }
        }
        return -1;
    }

    /*public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++){
            if (t.length() < 1) break;
            String c = s.substring(i, i+1);
            if (t.contains(c)){
                t = t.replaceFirst(c, "");
            } else {
                return false;
            }
        }
        return true;
    }*/

    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
            if (alphabet[t.charAt(i) - 'a'] < 0) return false;
        }
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int max = nums.length;
        for (int i = 0; i < max; i++) {
            if (nums[i] == nums[i + max / 2]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 回文
     */
    /*public int longestPalindrome(String s) {
        int len = 0;
        int[] a = new int[52];
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) - 'A') > 26) {
                a[s.charAt(i) - 'a' + 26]++;
            } else {
                a[s.charAt(i) - 'A']++;
            }
        }
        boolean hasSingle = false;
        for (int i = 0; i < 52; i++){
            if (!hasSingle && a[i] % 2 != 0){
                hasSingle = true;
            }
            len = len + a[i] / 2;
        }
        return hasSingle ? 2 * len + 1 : 2 * len;
    }*/
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hs.contains(s.charAt(i))) {
                hs.remove(s.charAt(i));
                count++;
            } else {
                hs.add(s.charAt(i));
            }
        }
        if (!hs.isEmpty()) return count * 2 + 1;
        return count * 2;
    }


}
