package com.cxl.interpreter;


import java.util.StringTokenizer;

public class Context {
    private StringTokenizer tokenizer;
    private String currentToken;

    public Context(String text) {
        tokenizer=new StringTokenizer(text);
        nextToken();
    }
    public String nextToken() {
        if (tokenizer.hasMoreTokens()){
            currentToken=tokenizer.nextToken();
        }else{
            currentToken=null;
        }
        return  currentToken;
    }

    public String getCurrentToken() {
        return currentToken;
    }
    public void checkToken(String token){
        if (!currentToken.equals(token)){
            System.out.println(token+"匹配上了");
        }
        nextToken();
    }
    public int currNumber() throws ParseException {
        int number;
        try {
            number=Integer.parseInt(currentToken);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
        return number;
    }
}
