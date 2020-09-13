import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.*;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class PyTest {
    public static void main(String[] args) {
//        String cmd = "python /home/cxl/cxl/test-demo/test/src/main/resources/yingjian.py";
//        System.out.println(cmd);
//        try {
//            Process proc = Runtime.getRuntime().exec(cmd);
//            InputStream inputStream = proc.getInputStream();
//            String flag;
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//            while ((flag = bufferedReader.readLine()) != null) {
//                System.out.println(flag);
//            }
//            bufferedReader.close();
//            int i = proc.waitFor();
//            System.out.println(i);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
        PyTest pyTest = new PyTest();


        while(true){
            String s = pyTest.execCmdWithResult();
            System.out.println(s);
        }

    }

    public String execCmdWithResult() {
        try {
            String command = "python /home/cxl/cxl/test-demo/test/src/main/resources/yingjian.py";
            //接收正常结果流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CommandLine commandline = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            //设置一分钟超时
            ExecuteWatchdog watchdog = new ExecuteWatchdog( 1000);
            exec.setWatchdog(watchdog);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            exec.setStreamHandler(streamHandler);
            exec.execute(commandline);
            //不同操作系统注意编码，否则结果乱码
            return outputStream.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
