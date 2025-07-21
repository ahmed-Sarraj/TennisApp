package domain.core.state;

import domain.core.model.Player;

/**
 * État Advantage dans le jeu de tennis.
 */
public record Advantage(Player advantaged) implements GameState {

    @Override
    public String score() {
        return "Advantage " + advantaged.name();
    }
}
