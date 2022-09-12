package backend.jangbogoProject.member.repository;

import backend.jangbogoProject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    
    @Override
    Optional<Member> findByEmail(String email);

    @Query(value = "SELECT name FROM member WHERE email = ?1", nativeQuery = true)
    String getNameFindByEmail(String email);
}
