package PacePitch.demo.service;

import PacePitch.demo.dto.request.CreatePitchingSessionRequest;
import PacePitch.demo.dto.response.PitchingSessionResponse;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.PitchingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PitchingSessionService {

    @Autowired
    private PitchingSessionRepository repository;

    public PitchingSessionResponse createSession(CreatePitchingSessionRequest request) {
        PitchingSessionEntity newSession = new PitchingSessionEntity(
                request.getTitle(),
                request.getMemo()
        );
        PitchingSessionEntity savedSession = repository.save(newSession);
        return new PitchingSessionResponse(savedSession.getId(), savedSession.getTitle(), savedSession.getMemo(), savedSession.getCreatedAt(), savedSession.getUpdatedAt());
    }

    public Page<PitchingSessionResponse> getSessions(int page, int size) {
        Page<PitchingSessionEntity> sessions = repository.findAll(PageRequest.of(page, size));
        return sessions.map(session -> new PitchingSessionResponse(session.getId(), session.getTitle(), session.getMemo(), session.getCreatedAt(), session.getUpdatedAt()));
    }

    public Optional<PitchingSessionResponse> getSessionById(UUID id) {
        return repository.findById(id)
                .map(session -> new PitchingSessionResponse(session.getId(), session.getTitle(), session.getMemo(), session.getCreatedAt(), session.getUpdatedAt()));
    }
}
