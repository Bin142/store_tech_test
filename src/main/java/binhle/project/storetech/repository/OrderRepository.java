package binhle.project.storetech.repository;

import binhle.project.storetech.entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<orders, Long> {
}
