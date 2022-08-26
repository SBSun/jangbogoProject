package backend.jangbogoProject.member.repository;

import backend.jangbogoProject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    
    @Override
    Optional<Member> findByEmail(String email);
}
