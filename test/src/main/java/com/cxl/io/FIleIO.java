package com.cxl.io;

import java.io.*;
import java.nio.ByteBuffer;

public class FIleIO {

    public void longWriteByte(long l, String filename) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeByte((int) l);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }
    }

    public static byte[] longToBytes(long l){
        ByteBuffer allocate = ByteBuffer.allocate(Long.BYTES);
        allocate.putLong(l);
        return allocate.array();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }
}
