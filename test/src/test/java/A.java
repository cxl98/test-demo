import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) {
        String app_dir = System.getProperty("APP_DIR");
        String property = System.getProperty("user.dir");
        Pattern compile = Pattern.compile("tty.");
        String property1 = System.getProperty("run-env");
        System.out.println(property1);
        String pattern = compile.pattern();
        System.out.println(pattern);
        System.out.println(property);
        System.out.println(app_dir);
    }
}
