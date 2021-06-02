package com.guava.test;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-05-25 14:45
 */
public class LeetCodeTest {
    /**
     * 测试两数之和
     */
    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        //断言
        assertArrayEquals(ints, new int[]{0, 1});
    }

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，
     * 请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
     *
     * @param nums   指定的数组
     * @param target 指定的数字
     * @return 返回符合条件的数组
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //如果目标值减去数组中的元素值在map中存在则返回
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            //不存在则存入
            map.put(nums[i], i);
        }
        //没有预期值返回一个空数组
        return new int[0];
    }


    /**
     * 两数相加
     * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
     * 并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 返回
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 测试无重复字符的最长子串
     */
    @Test
    public void testLengthOfLongestSubstring() {
        int i = lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
        TestCase.assertEquals(i, 3);
    }

    /**
     * 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s 给定的字符串
     * @return 返回最大不重复子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;//用于记录最大不重复子串的长度
        int left = 0;//滑动窗口左指针
        for (int i = 0; i < s.length(); i++) {
            /**
             *
             1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
             此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；
             2、如果当前字符 ch 包含在 map中，此时有2类情况：
             1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
             那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
             2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
             而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
             随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
             应该不变，left始终为2，子段变成 ba才对。
             为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
             另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
             因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
             */
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            //不管是否更新left，都要更新 s.charAt(i) 的位置！
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }

    /**
     * 高斯算法
     * 首项加末项乘以项数除以2
     */
    @Test
    public void sum() {
        int sum = 0;
        int n = 100;
        sum += (1 + n) * n / 2;
        System.out.println(sum);
        TestCase.assertEquals(sum, 5050);
    }

    /**
     * 测试寻找两个正序数组的中位数
     */
    @Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
        TestCase.assertEquals(result, 2.0);
    }

    /**
     * 寻找两个正序数组的中位数
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 返回
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) { // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) { // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                } // 奇数的话不需要考虑右半部分

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        //没有符合值返回-1
        return -1;
    }
}