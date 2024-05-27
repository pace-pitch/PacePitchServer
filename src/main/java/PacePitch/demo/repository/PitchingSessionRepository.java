package PacePitch.demo.repository;

import PacePitch.demo.model.PitchingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PitchingSessionRepository extends JpaRepository<PitchingSessionEntity, UUID> {
}
