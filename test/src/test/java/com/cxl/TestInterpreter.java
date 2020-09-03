package com.cxl;

import com.cxl.interpreter.Context;
import com.cxl.interpreter.Node;
import com.cxl.interpreter.ParseException;
import com.cxl.interpreter.ProgramNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestInterpreter {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            String text;
            while((text=bufferedReader.readLine())!=null){
                System.out.println("text=  "+text);
                Node node=new ProgramNode();
                node.parse(new Context(text));
                System.out.println("node=  "+node);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }finally {
            if (null!=bufferedReader){
                bufferedReader.close();
            }
        }
    }
}
