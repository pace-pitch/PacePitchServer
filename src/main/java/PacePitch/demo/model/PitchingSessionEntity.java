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
@Table(name = "pitching_sessions")  // 테이블 이름을 명시적으로 지정
public class PitchingSessionEntity {

    @Id
    @GeneratedValue //@GeneratedValue 어노테이션은 id 필드의 값이 자동으로 생성되도록 지정
    private UUID id;


    @Column(nullable = false)  // 일단 title 필드는 null 값을 못 가지도록 함
    private String title;

    private String memo;
    private long createdAt;
    private long updatedAt;

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
    public PitchingSessionEntity(UUID id) {
        this.id = id;
    }
}
