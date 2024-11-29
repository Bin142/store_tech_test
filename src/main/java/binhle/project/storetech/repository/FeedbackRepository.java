package binhle.project.storetech.repository;

import binhle.project.storetech.entity.feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<feedback, String> {
}
