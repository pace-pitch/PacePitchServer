package PacePitch.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pitching_sessions")
public class PitchingSessionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String memo;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IndividualPitchEntity> pitches = new ArrayList<>();

    public PitchingSessionEntity(String title, String memo) {
        this.title = title;
        this.memo = memo;
    }

    public void addPitch(IndividualPitchEntity pitch) {
        pitches.add(pitch);
        pitch.setSession(this);
    }
}
