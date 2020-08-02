import java.util.Stack;
import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) {
        Stack stack=new Stack();
        String app_dir = System.getProperty("APP_DIR");
        String property = System.getProperty("user.dir");
        Pattern compile = Pattern.compile("tty.");
        String property1 = System.getProperty("run-env");
        System.out.println(property1);
        String pattern = compile.pattern();
        System.out.println(pattern);
        System.out.println(property);
        System.out.println(app_dir);
        //mac地址的测试
        byte b= -100;//-89 A7 -93 //a3 //-100 9c
        String s="000000"+Integer.toHexString(b);
        System.out.println("xxx"+s);
        System.out.println(s.length());
        System.out.println(">>>>>"+s.substring(s.length()-2));

        String s1="A5 B5 6d";
        String s2 = s1.replaceAll(" ", "");
        String substring = s2.substring(2);
        System.out.println(substring);
        int a=10;
        int b1=150;
        int c=a*b1;
        System.out.println(c);
    }
}
