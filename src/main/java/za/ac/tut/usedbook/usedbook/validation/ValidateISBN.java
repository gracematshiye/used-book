package za.ac.tut.usedbook.usedbook.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateISBN {
    private  static Pattern pattern;
    private static Matcher matcher;

    public static boolean isISBN_valid(String isbn) throws ISBNException {
        String isbn_regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        pattern = Pattern.compile(isbn_regex);
        matcher = pattern.matcher(isbn);
        if(!matcher.matches()) {
            throw new ISBNException();
        }
        return true;
    }


    public static class ISBNException extends RuntimeException {
        public ISBNException() {
            super("Invalid ISBN number.");
        }
    }

}
