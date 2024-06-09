package PacePitch.demo.controller;

import PacePitch.demo.dto.request.CreatePitchingSessionRequest;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.service.PitchingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/create/session")
public class PitchingSessionController {

    @Autowired
    private PitchingSessionService service;

    @PostMapping
    public ResponseEntity<PitchingSessionEntity> createSession(@RequestBody CreatePitchingSessionRequest session) {
        PitchingSessionEntity savedSession = service.saveSession(session);
        return ResponseEntity.ok(savedSession);
    }

    @GetMapping
    public ResponseEntity<Page<PitchingSessionEntity>> getSessions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PitchingSessionEntity> sessions = service.getSessions(page, size);
        return ResponseEntity.ok(sessions);
    }
}
