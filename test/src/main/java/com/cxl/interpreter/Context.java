package com.cxl.interpreter;


import java.util.StringTokenizer;

public class Context {
    private StringTokenizer tokenizer;
    private String currentToken;

    public Context(String text) {
        tokenizer=new StringTokenizer(text);
        nextToken();
    }

    /**
     * 获取下一个标签
     *
     */
    public String nextToken() {
        if (tokenizer.hasMoreTokens()){
            currentToken=tokenizer.nextToken();
        }else{
            currentToken=null;
        }
        return  currentToken;
    }

    /**
     * 获取当前标签
     * @return 当前标签
     */
    public String getCurrentToken() {
        return currentToken;
    }

    /**
     *检查当前标签，然后获取下一个标签
     * @param token 标签
     */
    public void checkToken(String token){
        if (!currentToken.equals(token)){
            System.out.println(token+"匹配上了");
        }
        nextToken();
    }

    /**
     * 获取当前标签对应的数值
     * @return 当前标签所在的位置
     * @throws ParseException 抛出无法解析的异常
     */
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
