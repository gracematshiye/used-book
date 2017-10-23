package za.ac.tut.usedbook.usedbook.sendingmail;

import org.springframework.stereotype.Component;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Payment;
import za.ac.tut.usedbook.usedbook.entiy.Student;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by gracem on 2017/10/22.
 */
@Component
public class MailSender {

    public static void sendEmail(Payment payment, Student buyer, Student seller, Book book) throws Exception {

        final String username = "ubanotify@gmail.com";
        final String password = "UBA-notify";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            notifyStudent(messageForBuyer(payment, buyer, seller, book),
                    session, buyer.getEmail());

            notifyStudent(messageForSeller(payment, buyer, seller, book),
                    session, seller.getEmail());

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void notifyStudent(String msg, Session session, String emailAdress) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ubanotify@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailAdress));
        message.setSubject("Payment Notification");
        message.setText(msg);

        Transport.send(message);
    }

    private static String messageForSeller(Payment payment, Student buyer, Student seller, Book book) throws Exception {

       return "Used Book Application".toUpperCase() + "\n\n" +
               "Dear " + seller.getName() + " " + seller.getSurname() + "\n\n" +
                "Please take note that " + buyer.getName() + " " + buyer.getSurname() + " made a payment to your student account.\n\n" +
                "The payment details are as follows:\n" +
                "Payment reference:\t\t" + payment.getPaymentReference() + "\n" +
                "Payment date:\t\t\t\t" + payment.getPaymentDate() + "\n\n" +
                "Payment details\n" +
                "Amount:\t\t\t\t\tR " + payment.getAmount() + "\n" +
                "New Balance:\t\t\t\tR "+ seller.getFunds() + "\n" +
                "Title:\t\t\t\t\t\t" + book.getTitle() + "\n" +
                "Author:\t\t\t\t\t" + book.getAuthor() + "\n" +
                "ISBN:\t\t\t\t\t" + book.getIsbn() + "\n\n\n" +
                "Thanks\n" +
                "UBA";

    }

    private static String messageForBuyer(Payment payment, Student buyer, Student seller, Book book) throws Exception {
        return "Used Book Application".toUpperCase() + "\n\n" +
                "Dear " + buyer.getName() + " " + buyer.getSurname() + "\n\n" +
                "Please take note that you made a payment to " + seller.getName() + " " + seller.getSurname() + ".\n\n" +
                "The payment details are as follows:\n" +
                "Payment reference:\t\t" + payment.getPaymentReference() + "\n" +
                "Payment date:\t\t\t" + payment.getPaymentDate() + "\n\n" +
                "Payment details\n" +
                "Amount:\t\t\t\t\tR " + payment.getAmount() + "\n" +
                "New Balance:\t\t\tR "+ buyer.getFunds() + "\n" +
                "Title:\t\t\t\t\t" + book.getTitle() + "\n" +
                "Author:\t\t\t\t\t" + book.getAuthor() + "\n" +
                "ISBN:\t\t\t\t\t" + book.getIsbn() + "\n\n\n" +
                "Thanks\n" +
                "UBA";
    }
}
