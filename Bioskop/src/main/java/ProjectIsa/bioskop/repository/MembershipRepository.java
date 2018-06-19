package ProjectIsa.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

}
