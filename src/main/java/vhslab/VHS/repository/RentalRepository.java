package vhslab.VHS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vhslab.VHS.model.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    boolean existsByVhsIdAndRentalDateAndReturnDateIsNull(Long vhsId, LocalDate rentalDate);

    @Query("""
    SELECT COUNT(r) > 0
    FROM Rental r
    WHERE r.vhs.id = :vhsId
      AND r.rentalDate <= :newDueDate
      AND r.dueDate >= :newRentalDate
      AND r.returnDate IS NULL
    """)
    boolean existsOverlap(
            @Param("vhsId") Long vhsId,
            @Param("newRentalDate") LocalDate newRentalDate,
            @Param("newDueDate") LocalDate newDueDate
    );

    List<Rental> findAllByReturnDateIsNullAndDueDateBefore(LocalDate date);

    @Query("""
    SELECT COALESCE(SUM(r.lateFee), 0)
    FROM Rental r
    WHERE r.user.id = :userId
    AND r.lateFee IS NOT NULL
    """)
    BigDecimal sumLateFeesByUserId(@Param("userId") Long userId);
}
