package PacePitch.demo.service;

import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.PitchingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PitchingSessionService {

    @Autowired
    private PitchingSessionRepository repository;

    // save_session()는 PitchingSessionEntity 객체를 db에 저장
    public PitchingSessionEntity save_session(PitchingSessionEntity session) {
        // JPA의 save 메서드를 호출하여, 새로운 엔티티를 데이터베이스에 삽입하거나 기존 엔티티를 업데이트
        return repository.save(session);
    }
}
