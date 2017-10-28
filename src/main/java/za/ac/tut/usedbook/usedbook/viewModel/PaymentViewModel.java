package za.ac.tut.usedbook.usedbook.viewModel;

import za.ac.tut.usedbook.usedbook.entiy.Payment;

/**
 * Created by gracem on 2017/10/26.
 */
public class PaymentViewModel {
    private UserViewModel student;
    private Payment payment;
    private String message;

    public PaymentViewModel(UserViewModel student, Payment payment) {
        this.student = student;
        this.payment = payment;
        this.message = "Your Payment was successful. You'll receive email notification.";
    }

    public UserViewModel getStudent() {
        return student;
    }

    public void setStudent(UserViewModel student) {
        this.student = student;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
