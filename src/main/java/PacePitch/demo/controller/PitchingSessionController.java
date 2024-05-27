package PacePitch.demo.controller;

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

    // HTTP POST 요청을 처리하는 메서드임을 나타낸다.
    @PostMapping
    public ResponseEntity<PitchingSessionEntity> createSession(@RequestBody PitchingSessionEntity session) {
        PitchingSessionEntity savedSession = service.save_session(session);
        return ResponseEntity.ok(savedSession);
    }
}
