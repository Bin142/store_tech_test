package binhle.project.storetech.repository;

import binhle.project.storetech.entity.order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetailRepository extends JpaRepository<order_details, String> {
}
