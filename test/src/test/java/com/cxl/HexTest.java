package com.cxl;

import com.cxl.hex.HexUtil;

import java.nio.charset.StandardCharsets;

public class HexTest {
    public static void main(String[] args) {
        String str="�����J��";
        byte[] bytes = HexUtil.hexStringToBytes(str);
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
