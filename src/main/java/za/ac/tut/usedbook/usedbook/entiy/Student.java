package za.ac.tut.usedbook.usedbook.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gracem on 2017/09/24.
 */
@Entity
@Table(name = "STUDENT")
public class Student /*implements Serializable */{

    @Id
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "funds")
    private Double funds;
    @Column(name ="password")
    private String password;
    @Column(name = "logged_in")
    private Boolean loggedIn;
    @Column(name ="session_key")
    private String sessionKey;

    public Student() {
    }

    public Student(Integer studentId, String name, String surname, Double funds) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.funds = funds;
    }

    public Student(Integer studentId, String name, String surname, String email, Double funds, String password, Boolean loggedIn, String sessionKey) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.funds = funds;
        this.password = password;
        this.loggedIn = loggedIn;
        this.sessionKey = sessionKey;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.sessionKey = sessionKey;
    }
}
