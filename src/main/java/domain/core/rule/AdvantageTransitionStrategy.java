package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.Advantage;
import domain.core.state.Deuce;
import domain.core.state.GameState;
import domain.core.state.Win;

/**
 * Stratégie de transition pour l'état Advantage dans le jeu de tennis.
 * <p>
 * Cette classe implémente la logique métier permettant de passer de l'état Advantage à Win
 * si le joueur en avantage remporte le point, ou de revenir à Deuce sinon.
 * <ul>
 *   <li>Si le joueur en avantage gagne le point, il remporte la partie (état Win).</li>
 *   <li>Sinon, le jeu revient à l'état Deuce.</li>
 * </ul>
 */
public class AdvantageTransitionStrategy implements TransitionStrategy {
    @Override
    public GameState transition(GameState currentState, Player winner) {
        Advantage advantage = (Advantage) currentState;
        return (advantage.advantaged().equals(winner) ? new Win(winner) : new Deuce());
    }
}
