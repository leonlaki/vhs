package vhslab.VHS.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.VHS.dto.rental.RentalRequest;
import vhslab.VHS.dto.rental.RentalResponse;
import vhslab.VHS.model.Rental;
import vhslab.VHS.service.RentalService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Long id) {
        Rental rental = rentalService.getById(id);
        return ResponseEntity.ok(mapToResponse(rental));
    }

    @PostMapping("/add")
    public ResponseEntity<RentalResponse> rentVHS(@Valid @RequestBody RentalRequest request) {
        Rental rental = rentalService.rentVHS(request.getUserId(), request.getVhsId(), request.getRentalDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(rental));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<RentalResponse> returnVHS(@PathVariable Long id) {
        Rental rental = rentalService.returnVHS(id);
        return ResponseEntity.ok(mapToResponse(rental));
    }

    @GetMapping
    public ResponseEntity<List<RentalResponse>> getAllRentals() {
        List<RentalResponse> rentals = rentalService.findAll().stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(rentals);
    }

    private RentalResponse mapToResponse(Rental rental) {
        RentalResponse dto = new RentalResponse();
        dto.setRentalId(rental.getId());
        dto.setUserId(rental.getUser().getId());
        dto.setVhsId(rental.getVhs().getId());
        dto.setRentalDate(rental.getRentalDate());
        dto.setDueDate(rental.getDueDate());
        dto.setReturnDate(rental.getReturnDate());
        dto.setLateFee(rental.getLateFee());
        return dto;
    }

}
