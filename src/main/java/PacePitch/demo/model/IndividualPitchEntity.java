package PacePitch.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "individual_pitches")
public class IndividualPitchEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private PitchingSessionEntity session;  // This establishes the many-to-one relationship

    private double velocity;
    private PitchType pitchType;
    private String memo;
    private ThrowingHand throwingHand;
    private long createdAt;
    private long updatedAt;

    @Column(length = 1024)
    private String minioUrl;

    @Column(length = 1024)
    private String thumbnailUrl;

    public IndividualPitchEntity(double velocity, PitchType pitchType, String memo, ThrowingHand throwingHand, PitchingSessionEntity session) {
        this.velocity = velocity;
        this.pitchType = pitchType;
        this.memo = memo;
        this.throwingHand = throwingHand;
        this.session = session;
        long now = Instant.now().getEpochSecond();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now().getEpochSecond();
    }

    public void updatePitch(double velocity, PitchType pitchType, String memo, ThrowingHand throwingHand) {
        this.velocity = velocity;
        this.pitchType = pitchType;
        this.memo = memo;
        this.throwingHand = throwingHand;
        onUpdate();
    }
}
