package PacePitch.demo.repository;

import PacePitch.demo.model.IndividualPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualPitchRepository extends JpaRepository<IndividualPitchEntity, UUID> {
}
