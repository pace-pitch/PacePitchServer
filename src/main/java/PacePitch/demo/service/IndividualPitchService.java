package PacePitch.demo.service;

import PacePitch.demo.dto.IndividualPitchDTO;
import PacePitch.demo.dto.response.IndividualPitchResponse;
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
    private IndividualPitchRepository pitchRepository;
    @Autowired
    private PitchingSessionRepository sessionRepository;

    public IndividualPitchResponse createPitch(UUID sessionId, IndividualPitchDTO pitchDTO) {
        Optional<PitchingSessionEntity> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid session ID: " + sessionId);
        }
        PitchingSessionEntity session = sessionOptional.get();

        IndividualPitchEntity newPitch = new IndividualPitchEntity(
                pitchDTO.getVelocity(),
                pitchDTO.getPitchType(),
                pitchDTO.getMemo(),
                pitchDTO.getThrowingHand(),
                session
        );
        pitchRepository.save(newPitch);

        return new IndividualPitchResponse(
                newPitch.getId(),
                newPitch.getVelocity(),
                newPitch.getPitchType(),
                newPitch.getMemo(),
                newPitch.getThrowingHand()
        );
    }

    public IndividualPitchResponse updatePitch(UUID pitchId, IndividualPitchDTO pitchDTO) {
        Optional<IndividualPitchEntity> optionalPitch = pitchRepository.findById(pitchId);
        if (optionalPitch.isPresent()) {
            IndividualPitchEntity existingPitch = optionalPitch.get();
            existingPitch.updatePitch(pitchDTO.getVelocity(), pitchDTO.getPitchType(), pitchDTO.getMemo(), pitchDTO.getThrowingHand());
            pitchRepository.save(existingPitch);
            return new IndividualPitchResponse(
                    existingPitch.getId(),
                    existingPitch.getVelocity(),
                    existingPitch.getPitchType(),
                    existingPitch.getMemo(),
                    existingPitch.getThrowingHand()
            );
        } else {
            throw new IllegalArgumentException("Invalid pitch ID");
        }
    }
}
