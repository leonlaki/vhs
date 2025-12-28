package vhslab.VHS.dto.rental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalResponse {

    private Long rentalId;
    private Long userId;
    private Long vhsId;

    private LocalDate rentalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private BigDecimal lateFee;

    public RentalResponse() {}

    public RentalResponse(Long rentalId, Long userId,
                          Long vhsId, LocalDate rentalDate,
                          LocalDate dueDate, LocalDate returnDate, BigDecimal lateFee) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.vhsId = vhsId;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.lateFee = lateFee;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }
}
