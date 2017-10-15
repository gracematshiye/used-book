package za.ac.tut.usedbook.usedbook.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.ac.tut.usedbook.usedbook.entiy.Book;
import za.ac.tut.usedbook.usedbook.entiy.Student;
import za.ac.tut.usedbook.usedbook.repository.StudentRepository;
import za.ac.tut.usedbook.usedbook.service.BookService;

/**
 * Created by gracem on 2017/10/04.
 */
@Component
public class UBAStartupRunner implements CommandLineRunner {
//    private static final Logger slf4jLogger = LoggerFactory.getLogger(UBAStartupRunner.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        String student_tb = "";
        String book_tb = "";
        String payment_tb = "";

        String student_data = "";
        String book_data = "";
        String payment_data = "";

        Student student = new Student(2100, "Lebo", "Mmala", 400.80, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Book book = new Book("Java", "Thomas JR","ISBN-13: 978-0-596-52068-7","7th","lang","TPG",7.9, "ON-SALE","additionalIno", student.getStudentId());

        studentRepository.save(student);
        bookService.save(book, student);


        System.out.println("ApplicationStartupRunner run method Started !!\n");

        System.out.println(studentRepository.findAll().iterator().next().getLoggedIn());

    }
}
