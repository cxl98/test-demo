package com.cxl.adapter.sample3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties extends Properties implements FileIO {
    public void readFromFile(String fileName) throws IOException {
        load(new FileInputStream(fileName));
    }

    public void writeToFile(String fileName) throws IOException {
        store(new FileOutputStream(fileName),"cxl");
    }

    public void setValue(String key, String value) {
        setProperty(key,value);
    }

    public String getValue(String key) {
        return getProperty(key,"");
    }
}
