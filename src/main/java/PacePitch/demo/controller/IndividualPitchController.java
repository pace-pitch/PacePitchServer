package PacePitch.demo.controller;

import PacePitch.demo.dto.IndividualPitchDTO;
import PacePitch.demo.dto.response.IndividualPitchResponse;
import PacePitch.demo.service.IndividualPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/session")
public class IndividualPitchController {

    @Autowired
    private IndividualPitchService service;

    @PostMapping("/{sessionId}/pitch")
    public ResponseEntity<IndividualPitchResponse> createPitch(@PathVariable UUID sessionId, @RequestBody IndividualPitchDTO individualPitchDTO) {
        try {
            IndividualPitchResponse newPitch = service.createPitch(sessionId, individualPitchDTO);
            return ResponseEntity.ok(newPitch);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/pitch/{pitchId}")
    public ResponseEntity<IndividualPitchResponse> updatePitch(@PathVariable UUID pitchId, @RequestBody IndividualPitchDTO individualPitchDTO) {
        try {
            IndividualPitchResponse updatedPitch = service.updatePitch(pitchId, individualPitchDTO);
            return ResponseEntity.ok(updatedPitch);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{sessionId}/pitches")
    public ResponseEntity<Page<IndividualPitchResponse>> getPitchesBySession(
            @PathVariable UUID sessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<IndividualPitchResponse> pitches = service.getPitchesBySession(sessionId, page, size);
        return ResponseEntity.ok(pitches);
    }
}
