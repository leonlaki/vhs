package vhslab.VHS.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.VHS.dto.vhs.VHSCreateRequest;
import vhslab.VHS.dto.vhs.VHSResponse;
import vhslab.VHS.model.VHS;
import vhslab.VHS.service.VHSService;

import java.util.List;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {

    private VHSService vhsService;

    public VHSController(VHSService vhsService) {
        this.vhsService = vhsService;
    }

    @PostMapping("/add")
    public ResponseEntity<VHSResponse> createVhs(@Valid @RequestBody VHSCreateRequest request) {
        VHS vhs = vhsService.create(mapToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(vhs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VHSResponse> getVHS(@PathVariable Long id) {
        VHS vhs = vhsService.findById(id).orElseThrow(() -> new RuntimeException("VHS not found."));
        return ResponseEntity.ok(mapToResponse(vhs));
    }

    @GetMapping
    public ResponseEntity<List<VHSResponse>> getAllVhs() {
        List<VHSResponse> vhsList = vhsService.findAll().stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(vhsList);
    }


    private VHS mapToEntity(VHSCreateRequest request) {
        VHS vhs = new VHS();
        vhs.setTitle(request.getTitle());
        vhs.setGenre(request.getGenre());
        vhs.setReleaseYear(request.getReleaseYear());
        vhs.setDirector(request.getDirector());
        vhs.setDurationMinutes(request.getDurationMinutes());
        return vhs;
    }

    private VHSResponse mapToResponse(VHS vhs) {
        VHSResponse response = new VHSResponse();
        response.setId(vhs.getId());
        response.setTitle(vhs.getTitle());
        response.setGenre(vhs.getGenre());
        response.setReleaseYear(vhs.getReleaseYear());
        return response;
    }
}
