package za.ac.tut.usedbook.usedbook.validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gracem on 2017/09/30.
 */
public class PasswordEncoder {

    public String encode(Long studentId, String password) {
        String saltedPassword = studentId.toString() + password;
        return generateHash(saltedPassword);
    }

    public Boolean verifyPassword(String userDB, String userInput) {
        return userDB.equals(userInput);
    }

    public String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

}
