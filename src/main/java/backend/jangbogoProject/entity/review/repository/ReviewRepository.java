package backend.jangbogoProject.entity.review.repository;

import backend.jangbogoProject.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM review WHERE user_email = ?1", nativeQuery = true)
    void deleteByUserEmail(String user_email);

    List<Review> findAllByUserEmail(String userEmail);

    List<Review> findAllByMarketId(Long marketId);
}
