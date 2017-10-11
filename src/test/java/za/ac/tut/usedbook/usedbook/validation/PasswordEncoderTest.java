package za.ac.tut.usedbook.usedbook.validation;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gracem on 2017/09/30.
 */
public class PasswordEncoderTest {

    PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    public void shouldGenerateHash() throws Exception {
        String hash = passwordEncoder.generateHash("salt-password");

        assertThat(hash).isEqualTo(generateHash("salt-password"));
    }

    @Test
    public void shouldEncodeStudentNumberAndPasswordReturnHashedPassword() throws Exception {
        String saltedPassword = "210243" + "password1";

        String result = passwordEncoder.encode(210243L, "password1");

        assertThat(result).isEqualTo(generateHash(saltedPassword));

    }

    @Test
    public void shouldReturnTrue() throws Exception {
        String fromDB = generateHash("21025" + "pass01");
        String fromUser = generateHash("21025" + "pass01");

        Boolean isTheSame = passwordEncoder.verifyPassword(fromDB, fromUser);

        assertThat(isTheSame).isTrue();

    }
    @Test
    public void shouldReturnFalse() throws Exception {
        String fromDB = generateHash("21025" + "pass01");
        String fromUser = generateHash("21025" + "pass02");
        System.out.println(fromDB);
        Boolean isTheSame = passwordEncoder.verifyPassword(fromDB, fromUser);

        assertThat(isTheSame).isFalse();

    }

    private String generateHash(String input) {
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
