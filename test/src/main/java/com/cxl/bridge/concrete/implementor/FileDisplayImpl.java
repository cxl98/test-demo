package com.cxl.bridge.concrete.implementor;

import com.cxl.bridge.implementor.DisplayImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDisplayImpl extends DisplayImpl {
    private String fileName;
    private BufferedReader reader;
    private final int MAX_READAHEAD_LIMIT = 4096;//循环显示的极限（缓存大小限制）

    public FileDisplayImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void rawOpen() {
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.mark(MAX_READAHEAD_LIMIT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=-=-=-=-=-= " + fileName + " =-=-=-=-=-=");
    }

    @Override
    public void rawPrint() {
        try {
            String line;
            reader.reset();// 回到mark的位置
            while ((line=reader.readLine())!=null){
                System.out.println("> "+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rawClose() {
        System.out.println("=-=-=-=-=-=");
        if (reader!=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
