package PacePitch.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PitchType {
    FASTBALL_4_SEAM("Fastball", "4-seam"),
    SINKER_2_SEAM("Fastball", "2-seam"),
    CUTTER("Fastball", "Cutter"),
    CHANGEUP("Offspeed", "Changeup"),
    SPLIT_FINGER("Offspeed", "Split-finger"),
    FORKBALL("Offspeed", "Forkball"),
    SCREWBALL("Offspeed", "Screwball"),
    CURVEBALL("Breaking - Curveball Group", "Curveball"),
    KNUCKLE_CURVE("Breaking - Curveball Group", "Knuckle Curve"),
    SLOW_CURVE("Breaking - Curveball Group", "Slow Curve"),
    SLIDER("Breaking - Slider Group", "Slider"),
    SWEEPER("Breaking - Slider Group", "Sweeper"),
    SLURVE("Breaking - Slider Group", "Slurve");

    private final String category;
    private final String description;
}
