package com.cxl.algorithm;

public class LongestPalindrome {
    /**
     * i=0  len=1 len1=0 max=1 start=0 end=0
     * i=1  len=3 len1=0 max=3 start=1 end=1
     * i=2  len=  len1=  max=  start=  end=
     * i=3  len=  len1=  max=  start=  end=
     * i=4  len=  len1=  max=  start=  end=
     *
     * @param s="babad"
     * @return
     */
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s = "babab";
        String s1 = longestPalindrome.longestPalindrome(s);
        System.out.println(s1);
    }
}
