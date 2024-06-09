package PacePitch.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePitchingSessionRequest {
    private String title;
    private String memo;
}
