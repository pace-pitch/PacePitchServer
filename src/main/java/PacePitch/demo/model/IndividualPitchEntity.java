package PacePitch.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "individual_pitches")
public class IndividualPitchEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private PitchingSessionEntity session;

    private double velocity;
    private PitchType pitchType;
    private String memo;
    private ThrowingHand throwingHand;

    @Column(length = 1024)
    private String videoUrl;

    @Column(length = 1024)
    private String thumbnailUrl;

    public IndividualPitchEntity(double velocity, PitchType pitchType, String memo, ThrowingHand throwingHand, PitchingSessionEntity session) {
        this.velocity = velocity;
        this.pitchType = pitchType;
        this.memo = memo;
        this.throwingHand = throwingHand;
        this.session = session;
    }
}