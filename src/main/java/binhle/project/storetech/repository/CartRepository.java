package binhle.project.storetech.repository;

import binhle.project.storetech.entity.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<category, String> {

}
