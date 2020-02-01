package com.cxl;

import com.cxl.adapter.sample4.FileIO;
import com.cxl.adapter.sample4.FileProperties;

import java.io.IOException;

public class FileAdapter1 {
    public static void main(String[] args) {
        FileIO fileIO=new FileProperties();
        try {
            fileIO.readFromFile("/home/cxl/cxl/test-demo/test/src/main/java/com/cxl/adapter/sample3/file.txt");
            fileIO.setValue("ccc","333");
            fileIO.writeToFile("/home/cxl/cxl/test-demo/test/src/main/java/com/cxl/adapter/sample3/newFile1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
