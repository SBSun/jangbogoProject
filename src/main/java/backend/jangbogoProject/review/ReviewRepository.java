package backend.jangbogoProject.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review where review.id = ?1", nativeQuery = true)
    public List<Review> findAllByMemberId(int id);

    @Query(value = "SELECT * FROM review WHERE review.M_SEQ = ?1", nativeQuery = true)
    public List<Review> findAllByMarketSerialNum(int marketSerialNum);
}
