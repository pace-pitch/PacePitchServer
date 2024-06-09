package PacePitch.demo.repository;

import PacePitch.demo.model.PitchingSessionEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PitchingSessionRepository extends JpaRepository<PitchingSessionEntity, UUID> {
    @NotNull
    Page<PitchingSessionEntity> findAll(@NotNull Pageable pageable);
}
