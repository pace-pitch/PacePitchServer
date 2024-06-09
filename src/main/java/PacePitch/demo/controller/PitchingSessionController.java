package PacePitch.demo.controller;

import PacePitch.demo.dto.request.CreatePitchingSessionRequest;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.service.PitchingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create/session")
public class PitchingSessionController {

    @Autowired
    private PitchingSessionService service;

    @PostMapping
    public ResponseEntity<PitchingSessionEntity> createSession(@RequestBody CreatePitchingSessionRequest session) {
        PitchingSessionEntity savedSession = service.saveSession(session);
        return ResponseEntity.ok(savedSession);
    }
}
