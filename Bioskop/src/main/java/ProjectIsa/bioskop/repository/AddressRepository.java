package ProjectIsa.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.Adresa;

public interface AddressRepository extends JpaRepository<Adresa, Long> {
	Adresa findByCityAndStreet(String city, String street);
}
