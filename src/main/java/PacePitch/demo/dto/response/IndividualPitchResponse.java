package PacePitch.demo.dto.response;

import PacePitch.demo.model.PitchType;
import PacePitch.demo.model.ThrowingHand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class IndividualPitchResponse {
    private UUID id;
    private double velocity;
    private PitchType pitchType;
    private String memo;
    private ThrowingHand throwingHand;
}

