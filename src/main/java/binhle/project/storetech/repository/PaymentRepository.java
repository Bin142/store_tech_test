package binhle.project.storetech.repository;

import binhle.project.storetech.entity.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<payment, String> {
}
