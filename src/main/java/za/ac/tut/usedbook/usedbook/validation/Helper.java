package za.ac.tut.usedbook.usedbook.validation;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by gracem on 2017/09/30.
 */
public class Helper {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String encodeStringToBase64(String plainString) {
        byte[] plainCredsBytes = plainString.getBytes();
        return DatatypeConverter.printBase64Binary(plainCredsBytes);
    }

    public static String decodeBase64ToString(String base64) {
        return new String(DatatypeConverter.parseBase64Binary(base64));
    }

    public static String hashPassword(String plainString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainString.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        return sdf.format(new Date());
    }


    public static String generatePaymentReference() throws Exception  {
        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return (builder.toString());
    }

    public static double getDoubleForm(Double price) throws Exception  {
        String pattern = "0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(price);
        return Double.valueOf(format);
    }

}
