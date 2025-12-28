package vhslab.VHS.controller;

import jakarta.validation.Valid;
import org.hibernate.engine.spi.Resolution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.VHS.dto.user.UserCreateRequest;
import vhslab.VHS.dto.user.UserResponse;
import vhslab.VHS.model.User;
import vhslab.VHS.service.RentalService;
import vhslab.VHS.service.UserService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    private RentalService rentalService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = userService.create(mapToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
        return ResponseEntity.ok(mapToResponse(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.findAll().stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/debt")
    public ResponseEntity<BigDecimal> getUserDebt(@PathVariable Long id) {
        BigDecimal debt = rentalService.getTotalDebtForUser(id);
        return ResponseEntity.ok(debt);
    }

    private User mapToEntity(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        return user;
    }

    private UserResponse mapToResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
