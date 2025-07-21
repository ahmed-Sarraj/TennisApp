package domain.core.state;


/**
 * État Deuce dans le jeu de tennis.
 */
public record Deuce() implements GameState {
    @Override
    public String score() {
        return "Deuce";
    }


}
