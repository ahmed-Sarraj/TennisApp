package domain.core.state;

import domain.core.model.Player;

/**
 * État Win dans le jeu de tennis.
 */
public record Win(Player winner) implements GameState {
    @Override
    public String score() {
        return winner.name() + " wins the game";
    }
}
