package PacePitch.demo.service;

import PacePitch.demo.model.IndividualPitchEntity;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.IndividualPitchRepository;
import PacePitch.demo.repository.PitchingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class IndividualPitchService {
    @Autowired
    private IndividualPitchRepository repository;

//    public IndividualPitchEntity save_pitch(UUID sessionId, IndividualPitchEntity individualPitch) {
//        Optional<PitchingSessionEntity> session = pitchingSessionReposity.findById(sessionId);
//        if (session.isPresent()) {
//            individualPitch.setSession(session.get());
//            return repository.save(individualPitch);
//        } else {
//            throw new IllegalArgumentException("Invalid session ID");
//        }
//    }
    public IndividualPitchEntity updatePitch(UUID videoId, IndividualPitchEntity pitchData) {
        Optional<IndividualPitchEntity> optionalPitch = repository.findById(videoId);
        if (optionalPitch.isPresent()) {
            IndividualPitchEntity existingPitch = optionalPitch.get();
            existingPitch.setVelocity(pitchData.getVelocity());
            existingPitch.setPitchType(pitchData.getPitchType());
            existingPitch.setMemo(pitchData.getMemo());
            existingPitch.setThrowingHand(pitchData.getThrowingHand());
            existingPitch.setUpdatedAt(System.currentTimeMillis() / 1000);  // 현재 시간을 초 단위로 저장
            return repository.save(existingPitch);
        } else {
            throw new IllegalArgumentException("Invalid video ID");
        }
    }
}
