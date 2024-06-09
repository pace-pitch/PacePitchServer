package PacePitch.demo.service;

import PacePitch.demo.dto.request.CreatePitchingSessionRequest;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.PitchingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PitchingSessionService {

    @Autowired
    private PitchingSessionRepository repository;

    public PitchingSessionEntity saveSession(CreatePitchingSessionRequest session) {
        PitchingSessionEntity pitchingSessionEntity = new PitchingSessionEntity(
            session.getTitle(),
            session.getMemo()
        );
        return repository.save(pitchingSessionEntity);
    }

    public Page<PitchingSessionEntity> getSessions(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}
