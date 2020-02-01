package com.cxl.adapter.sample4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileProperties extends FileIO {
    private Properties properties;

    public FileProperties() {
        this.properties =new Properties();
    }

    public void readFromFile(String fileName) throws IOException {
        properties.load(new FileInputStream(fileName));
    }

    public void writeToFile(String fileName) throws IOException {
        properties.store(new FileOutputStream(fileName),"cxl");
    }

    public void setValue(String key, String value) {
        properties.setProperty(key,value);
    }
}
