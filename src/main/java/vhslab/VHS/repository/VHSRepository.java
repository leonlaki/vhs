package vhslab.VHS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.VHS.model.VHS;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
}
