package vhslab.VHS.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vhslab.VHS.handler.VHSAlreadyRentedException;
import vhslab.VHS.model.Rental;
import vhslab.VHS.model.User;

import vhslab.VHS.model.VHS;
import vhslab.VHS.repository.RentalRepository;
import vhslab.VHS.repository.UserRepository;
import vhslab.VHS.repository.VHSRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {

    private static final int RENTAL_DAYS = 7;
    private static final BigDecimal DAILY_LATE_FEE = new BigDecimal("2.00");

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final VHSRepository vhsRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, VHSRepository vhsRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.vhsRepository = vhsRepository;
    }

    public Rental create(Rental rental) {
        return rentalRepository.save(rental);
    }

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental with id " + id + " not found."));
    }

    //Rental Constraint
    @Transactional
    public Rental rentVHS(Long userId, Long vhsId, LocalDate rentalDate) {

        //Due date calculation
        LocalDate dueDate = rentalDate.plusDays(RENTAL_DAYS);

        if(rentalDate.isAfter(dueDate)) {
            throw new IllegalArgumentException("Rental date has to be before due date.");
        }

        boolean overlapConflict = rentalRepository.existsOverlap(vhsId, rentalDate, dueDate);

        if(overlapConflict) {
            throw new VHSAlreadyRentedException();
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        VHS vhs = vhsRepository.findById(vhsId).orElseThrow(() -> new RuntimeException("VHS not found."));

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setVhs(vhs);
        rental.setRentalDate(rentalDate);
        rental.setDueDate(dueDate);

        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental returnVHS(Long rentalId) {
        Rental rental = getById(rentalId);

        if(rental.getReturnDate() != null) {
            throw new RuntimeException("VHS " + rentalId + " has already been returned.");
        }

        LocalDate today = LocalDate.now();
        rental.setReturnDate(today);

        //Late fee calculations
        if(today.isAfter(rental.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(rental.getDueDate(), today);
            BigDecimal lateFee = DAILY_LATE_FEE.multiply(BigDecimal.valueOf(daysLate));
            rental.setLateFee(lateFee);
        } else {
            rental.setLateFee(BigDecimal.ZERO);
        }
        return rentalRepository.save(rental);
    }

}
