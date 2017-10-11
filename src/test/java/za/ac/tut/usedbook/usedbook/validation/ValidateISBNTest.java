package za.ac.tut.usedbook.usedbook.validation;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;

public class ValidateISBNTest {

    private ValidateISBN validateISBN;

    @Before
    public void setUp() throws Exception {
        validateISBN = new ValidateISBN();
    }

    @Test
    public void shouldReturnTrueWhenISBN13And10AreCorrect() throws Exception {
        String isbn10_1 = "0-596-52068-9";
        String isbn10_2 = "0 512 52068 9";
        String isbn10_3 = "ISBN-10 0-596-52068-9";
        String isbn10_4 = "ISBN-10: 0-596-52068-9";

        String isbn13_1 = "ISBN 978-0-596-52068-7";
        String isbn13_2 = "ISBN-13: 978-0-596-52068-7";
        String isbn13_3 = "978 0 596 52068 7";
        String isbn13_4 = "9780596520687";

        assertThat(validateISBN.isISBN_valid(isbn10_1)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn10_2)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn10_3)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn10_4)).isTrue();

        assertThat(validateISBN.isISBN_valid(isbn13_1)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn13_2)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn13_3)).isTrue();
        assertThat(validateISBN.isISBN_valid(isbn13_4)).isTrue();

    }

    @Test
    public void shouldThrowExceptionGivenInvalidISBN10() throws Exception {
        String isbn10 = "11 5122 52068 9";
        testISBN(isbn10);

    }
    @Test
    public void shouldThrowExceptionGivenInvalidISBN13() throws Exception {
        String isbn10 = "ISBN-12: 978-0-596-52068-7";
        testISBN(isbn10);

    }
    private void testISBN(String isbn) {
        try {
            validateISBN.isISBN_valid(isbn);
            fail("ISBNException was not thrown");
        } catch (ValidateISBN.ISBNException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid ISBN number.");
        }
    }

}
