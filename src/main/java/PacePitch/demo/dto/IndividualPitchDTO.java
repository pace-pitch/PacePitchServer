package PacePitch.demo.dto;

import PacePitch.demo.model.PitchType;
import PacePitch.demo.model.ThrowingHand;
import lombok.Data;

@Data
public class IndividualPitchDTO {
    private double velocity;
    private PitchType pitchType;
    private String memo;
    private ThrowingHand throwingHand;
    private String videoUrl;
}
