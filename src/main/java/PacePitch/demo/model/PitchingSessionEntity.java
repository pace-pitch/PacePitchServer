package PacePitch.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pitching_sessions")
public class PitchingSessionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String memo;
    private long createdAt;
    private long updatedAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IndividualPitchEntity> pitches = new ArrayList<>();

    public PitchingSessionEntity(String title, String memo) {
        this.title = title;
        this.memo = memo;
        long now = Instant.now().getEpochSecond();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == 0) {
            long now = Instant.now().getEpochSecond();
            createdAt = now;
            updatedAt = now;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now().getEpochSecond();
    }

    public void addPitch(IndividualPitchEntity pitch) {
        pitches.add(pitch);
        pitch.setSession(this);
    }
}
