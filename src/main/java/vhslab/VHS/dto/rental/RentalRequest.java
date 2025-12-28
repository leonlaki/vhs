package vhslab.VHS.dto.rental;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RentalRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long vhsId;
    @NotNull
    private LocalDate rentalDate;

    public RentalRequest() {}

    public RentalRequest(Long userId, Long vhsId, LocalDate rentalDate) {
        this.userId = userId;
        this.vhsId = vhsId;
        this.rentalDate = rentalDate;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
