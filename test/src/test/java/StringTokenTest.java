import com.cxl.interpreter.Context;
import com.cxl.interpreter.ParseException;

public class StringTokenTest {
    public static void main(String[] args) throws ParseException {
        Context context=new Context("public void main(String[] args) " +
                "{ String xxx = "+"xxx "+" }");
        for (int i = 0; i <10 ; i++) {

            System.out.println(context.getCurrentToken());
            context.nextToken();
        }

    }
}
