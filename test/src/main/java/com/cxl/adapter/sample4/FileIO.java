package com.cxl.adapter.sample4;

import java.io.IOException;

public abstract class FileIO {
    public abstract void readFromFile(String fileName)throws IOException;
    public abstract void writeToFile(String fileName) throws IOException;
    public abstract void setValue(String key,String value);
}
