package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.GameState;

/**
 * Stratégie de transition pour l'état Win dans le jeu de tennis.
 * <p>
 * Cette classe représente la logique métier pour l'état Win: aucune transition n'est effectuée,
 * l'état reste inchangé car la partie est terminée.
 */

public class WinTransitionStrategy implements TransitionStrategy {
    @Override
    public GameState transition(GameState currentState, Player winner) {
        return currentState;
    }
}
