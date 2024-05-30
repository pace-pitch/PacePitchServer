package PacePitch.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor  //  모든 필드를 파라미터로 받는 생성자
@Table(name = "individual_pitches")
public class IndividualPitchEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private PitchingSessionEntity session;

    private double velocity;
    private String pitchType;
    private String memo;
    private String throwingHand;
    private long createdAt;
    private long updatedAt;

    @Column(length = 1024)
    private String minioUrl;

    @Column(length = 1024)
    private String thumbnailUrl;
    @PrePersist
    protected void onCreate() {
        long now = Instant.now().getEpochSecond();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now().getEpochSecond();
    }
}
