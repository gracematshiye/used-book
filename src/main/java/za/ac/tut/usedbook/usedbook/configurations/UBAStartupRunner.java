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

        Student student1 = new Student(211238119, "Richard", "Sitole", "muphahlo@gmail.com", 600.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student2 = new Student(211239360, "Khomotso", "Modika", "khomotsomokgadi@gmail.com", 600.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student3 = new Student(211249226, "Christopher", "Khosa", "Khosachristopher@gmail.com", 700.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student4 = new Student(214004631, "Katlego", "Thongwane", "katlego209@gmail.com", 700.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student5 = new Student(213639480, "Nonduduzo", "Masilela", "nonduduzomasilela03@gmail.com", 500.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student7 = new Student(211108711, "Derrick", "Rasekgokga", "derrickrasekgokga@gmail.com", 400.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);
        Student student6 = new Student(210253009, "Grace", "Matshiye", "gracematshiye@gmail.com", 300.00, "28a4565a4953cb4e7e23317ba0504f4c",false, null);

        Book book1 = new Book("Java", "Thomas JR","ISBN-13: 978-0-596-52068-7","7th","lang","TPG",70.00, "ON-SALE","additionalIno", student1.getStudentId());
        Book book2= new Book("C#", "Thomas JR","ISBN-13: 978-0-596-52068-8","7th","lang","TPG",110.00, "ON-SALE","additionalIno", student3.getStudentId());

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        studentRepository.save(student7);

        bookService.save(book1, student1);
        bookService.save(book2, student3);

        System.out.println("ApplicationStartupRunner run method Started !!\n");
        System.out.println(studentRepository.findAll().iterator().next().getLoggedIn());

    }
}
