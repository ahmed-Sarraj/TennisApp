package domain.port;

import domain.core.state.GameState;

import java.util.List;

/**
 * Port d'entrée pour jouer le jeu de tennis.
 */
public interface TennisGamePort {
    List<String> play(String input, char player1, char player2);
}

