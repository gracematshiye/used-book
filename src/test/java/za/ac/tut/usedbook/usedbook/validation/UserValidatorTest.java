package za.ac.tut.usedbook.usedbook.validation;

import za.ac.tut.usedbook.usedbook.entiy.Student;

/**
 * Created by gracem on 2017/09/30.
 */
public class UserValidatorTest {

    private Student getValidUser() {
        return new Student(21025,"greg","makala", "makala@gmail.com",70.40, "pass01", true, "session");
    }
}
