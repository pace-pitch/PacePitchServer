package PacePitch.demo.repository;

import PacePitch.demo.model.IndividualPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IndividualPitchRepository extends JpaRepository<IndividualPitchEntity, UUID> {
    @Query("SELECT ip.thumbnailUrl, ps.title FROM IndividualPitchEntity ip JOIN ip.session ps WHERE ip.session.id = :sessionId AND ip.createdAt = (SELECT MIN(ip2.createdAt) FROM IndividualPitchEntity ip2 WHERE ip2.session.id = :sessionId)")
    Object[] findOldestThumbnailUrlAndSessionTitle(@Param("sessionId") UUID sessionId);
}
