package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.Advantage;
import domain.core.state.GameState;

/**
 * Stratégie de transition pour l'état Deuce dans le jeu de tennis.
 * <p>
 * Cette classe gère la logique métier permettant de passer de l'état Deuce à Advantage
 * lorsque l'un des joueurs remporte le point.
 * <ul>
 *   <li>Si un joueur gagne le point en situation de Deuce, il obtient l'avantage (état Advantage).</li>
 * </ul>
 */
public class DeuceTransitionStrategy implements TransitionStrategy {
    @Override
    public GameState transition(GameState currentState, Player winner) {
        return new Advantage(winner);
    }
}
