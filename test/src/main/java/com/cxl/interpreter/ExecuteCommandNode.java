package com.cxl.interpreter;

public class ExecuteCommandNode implements Node{
    private String name;
    @Override
    public void parse(Context context) throws ParseException {
        name=context.getCurrentToken();
        context.checkToken(name);
        if (!"go".equals(name)&&!"r".equals(name)&&!"l".equals(name)){
            throw new ParseException(name+ "is undefined");
        }
    }

    @Override
    public String toString() {
        return "ExecuteCommandNode{" +
                "name='" + name + '\'' +
                '}';
    }
}
