package priv.ga0weI.demoshrio;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class Unserializexxx {
    public static void main(String[] args) throws  Exception {
        ObjectInputStream ois = new CompatibleInputStream(new FileInputStream("xxxxx.ser"));
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("xxxxx.ser"));
        PriorityQueue object = (PriorityQueue)ois.readObject();
        System.out.println(object);
    }
}
