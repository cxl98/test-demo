package com.cxl.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cxl
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, res = 0;
        while (i < n && j < n){
            if (!set.contains(s.charAt(i))){
                set.add(s.charAt(i++));
                res=Math.max(res,i-j);
            }else {
                set.remove(s.charAt(j++));
            }
        }
        return res;
    }
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int res=0;
        Map<Character,Integer> map =new HashMap<>();
        for (int i = 0,j=0; i < n; i++) {
            if (map.containsKey(s.charAt(i))){
                j= Math.max(map.get(s.charAt(i)),i);
            }
            res=Math.max(res,i-j+1);
            map.put(s.charAt(i),i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring=new LengthOfLongestSubstring();
        String s="abcabcbb";
        int i = lengthOfLongestSubstring.lengthOfLongestSubstring1(s);
        System.out.println(i);
    }
}
