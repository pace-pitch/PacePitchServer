package PacePitch.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PitchingSessionResponse {
    private UUID id;
    private String title;
    private String memo;
    private long created_at;
    private long updated_at;
}
