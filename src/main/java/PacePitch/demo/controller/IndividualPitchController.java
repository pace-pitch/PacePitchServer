package PacePitch.demo.controller;

import PacePitch.demo.model.IndividualPitchEntity;
import PacePitch.demo.repository.IndividualPitchRepository;
import PacePitch.demo.service.IndividualPitchService;
import PacePitch.demo.service.PitchingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/session")
public class IndividualPitchController {

    @Autowired
    private IndividualPitchService service;


    @PostMapping("/{videoId}/upload/info")
    public ResponseEntity<IndividualPitchEntity> updateIndividualPitch(@PathVariable UUID videoId, @RequestBody IndividualPitchEntity individualPitch) {
        try {
            IndividualPitchEntity updatedIndividualPitch = service.updatePitch(videoId, individualPitch);
            return ResponseEntity.ok(updatedIndividualPitch);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
