package za.ac.tut.usedbook.usedbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.tut.usedbook.usedbook.entiy.Payment;

import java.util.List;

/**
 * Created by gracem on 2017/10/12.
 */
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Payment save(Payment payment);
    Payment findById(Integer id);
    List<Payment> findAll();
}
