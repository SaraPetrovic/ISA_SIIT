package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ConfirmationCode;

public interface ConfirmationCodesDBRepository extends JpaRepository<ConfirmationCode, Long> {
	
	@Override
	ConfirmationCode save(ConfirmationCode code);
	ConfirmationCode findByCode(String code);
	@Override
	List<ConfirmationCode> findAll();
	@Override
	void delete(ConfirmationCode code);
	
}
