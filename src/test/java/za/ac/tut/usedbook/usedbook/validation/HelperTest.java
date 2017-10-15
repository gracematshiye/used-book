package za.ac.tut.usedbook.usedbook.validation;

import org.junit.Test;
import za.ac.tut.usedbook.usedbook.TestBase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gracem on 2017/09/30.
 */
public class HelperTest extends TestBase {

    @Test
    public void shouldEncryptStringToBase64() throws Exception {

        String plainStr = "To Be Coded";
        String expected = getEncodeToBAse64(plainStr);

        System.out.println(getEncodeToBAse64("21ff9578-997f-4389-8351-fae5ac516e1d"));
        String result = Helper.encodeStringToBase64(plainStr);
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void shouldDecryptBase64ToString() throws Exception {

        String expectedStr = "To Be Decoded";
        String base64 = getEncodeToBAse64(expectedStr);

        String result = Helper.decodeBase64ToString(base64);
        assertThat(result).isEqualTo(expectedStr);
    }

    @Test
    public void shouldHashPassword() throws Exception {
        String plainStr = "To_Be1Hashed";
        String hashStr = getHashPasswordToHexFormat(plainStr);

        System.out.println(Helper.hashPassword("pass01"));
        String result = Helper.hashPassword(plainStr);
        assertThat(result).isEqualTo(hashStr);


    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    @Test
    public void randomAlphaNumeric() throws Exception  {
        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        System.out.println(builder.toString());
    }

}
