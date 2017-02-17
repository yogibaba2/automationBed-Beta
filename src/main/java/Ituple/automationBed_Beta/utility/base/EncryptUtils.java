package Ituple.automationBed_Beta.utility.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class EncryptUtils {
    public static final String DEFAULT_ENCODING = "UTF-8"; 
    static Base64 enc = new Base64();
   // static Base64 dec = new Base64Decoder();

    public static String base64encode(String text) {
        try {
            return enc.encodeToString(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }//base64encode

    public static String base64decode(String text) {
        try {
            return new String(enc.decode(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }//base64decode
    
public static String xorMessage(String message, String key) {
        try {
            if (message == null || key == null) return null;

            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char)(mesg[i] ^ keys[i % kl]);
            }//for i

            return new String(newmsg);
        } catch (Exception e) {
            return null;
        }
    }//xorMessage
}//class