package za.ac.tut.usedbook.usedbook;

import za.ac.tut.usedbook.usedbook.entiy.Student;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gracem on 2017/09/30.
 */
public class TestBase {

    public String getEncodeToBAse64(String plain){
        byte[] plainCredsBytes = plain.getBytes();
        return DatatypeConverter.printBase64Binary(plainCredsBytes);
    }

    public String getHashPasswordToHexFormat(String plain) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plain.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }

//    public User getValidCredentialAndExistingStudent(){
//        Student student = getExistingStudent();
//        return new User(1L,student, "pass01",true, "USER", null);
//    }

    public Student getExistingStudent(){
        return  new Student(2100, "Lebo", "Mmala", 400.80, "pass01",true, null);
    }
}
