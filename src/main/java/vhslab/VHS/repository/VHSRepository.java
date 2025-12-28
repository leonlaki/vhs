package vhslab.VHS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.VHS.model.VHS;
import vhslab.VHS.utility.Genre;

import java.util.List;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {

    List<VHS> findAllByGenre(Genre genre);

}
