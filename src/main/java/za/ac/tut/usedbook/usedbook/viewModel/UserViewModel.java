package za.ac.tut.usedbook.usedbook.viewModel;

import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.validation.Helper;

/**
 * Created by gracem on 2017/09/30.
 */
public class UserViewModel {

    private Integer studentId;
    private String name;
    private String surname;
    private Double funds;
    private Boolean loggedIn;
    private String sessionKey;

    public UserViewModel(Student student) {
        this.studentId = student.getStudentId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.funds = student.getFunds();
        this.loggedIn = student.getLoggedIn();
        setSessionKey(student.getSessionKey());
        this.sessionKey = getSessionKey();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        if(sessionKey.equals(null)) {
            this.sessionKey = sessionKey;
        } else {
            this.sessionKey = Helper.encodeStringToBase64(sessionKey);
        }
    }
}
