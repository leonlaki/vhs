package vhslab.VHS;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vhslab.VHS.model.User;
import vhslab.VHS.model.VHS;
import vhslab.VHS.repository.UserRepository;
import vhslab.VHS.repository.VHSRepository;
import vhslab.VHS.service.RentalService;
import vhslab.VHS.utility.Genre;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VHSRepository vhsRepository;

    @Test
    void shouldNotAllowOverlappingRental() {
        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setEmail("test@test.com");
        user.setPhoneNumber("+385915102971");
        user = userRepository.save(user);

        VHS vhs = new VHS();
        vhs.setTitle("The Matrix");
        vhs.setGenre(Genre.SCI_FI);
        vhs.setReleaseYear(1999);
        vhs.setDurationMinutes(130);
        vhs.setDirector("Wachowski");
        vhs = vhsRepository.save(vhs);

        final Long userId = user.getId();
        final Long vhsId = vhs.getId();

        LocalDate rentalDate = LocalDate.now();

        rentalService.rentVHS(user.getId(), vhs.getId(), rentalDate);

        assertThrows(RuntimeException.class, () ->
                rentalService.rentVHS(userId, vhsId, rentalDate));
    }

}
