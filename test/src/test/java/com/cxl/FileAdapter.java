package com.cxl;

import com.cxl.adapter.sample3.FileIO;
import com.cxl.adapter.sample3.FileProperties;

import java.io.IOException;

public class FileAdapter {
    public static void main(String[] args) {
        FileIO fileIO=new FileProperties();
        try {
            fileIO.readFromFile("/home/cxl/cxl/test-demo/test/src/main/java/com/cxl/adapter/sample3/file.txt");
            fileIO.setValue("bbb","222");
            fileIO.writeToFile("/home/cxl/cxl/test-demo/test/src/main/java/com/cxl/adapter/sample3/newFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
