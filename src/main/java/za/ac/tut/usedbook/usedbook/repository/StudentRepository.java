package za.ac.tut.usedbook.usedbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.tut.usedbook.usedbook.entiy.Student;

/**
 * Created by gracem on 2017/09/28.
 */
@Transactional
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student save(Student student);
    Student findByStudentId(Integer studentId);

    Student findBySessionKey(String key);
}
