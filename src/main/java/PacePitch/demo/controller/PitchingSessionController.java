package PacePitch.demo.controller;

import PacePitch.demo.dto.request.CreatePitchingSessionRequest;
import PacePitch.demo.dto.response.PitchingSessionResponse;
import PacePitch.demo.service.PitchingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
public class PitchingSessionController {

    @Autowired
    private PitchingSessionService service;

    @PostMapping
    public ResponseEntity<PitchingSessionResponse> createSession(@RequestBody CreatePitchingSessionRequest request) {
        PitchingSessionResponse response = service.createSession(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PitchingSessionResponse>> getSessions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PitchingSessionResponse> responses = service.getSessions(page, size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PitchingSessionResponse> getSessionById(@PathVariable UUID id) {
        return service.getSessionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
