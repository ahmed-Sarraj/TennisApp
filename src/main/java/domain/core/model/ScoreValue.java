package domain.core.model;

/**
 * Représente un score de tennis.
 */
public enum ScoreValue {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40);

    private final int points;

    ScoreValue(int points) {
        this.points = points;
    }


    public String display() {
        return switch (this) {
            case LOVE -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
        };
    }
}
