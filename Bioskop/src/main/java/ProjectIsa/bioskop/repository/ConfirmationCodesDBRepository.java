package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ConfirmationCode;

public interface ConfirmationCodesDBRepository extends JpaRepository<ConfirmationCode, Long> {
	
	ConfirmationCode save(ConfirmationCode code);
	ConfirmationCode findByCode(String code);
	List<ConfirmationCode> findAll();
	void delete(ConfirmationCode code);
	
}
