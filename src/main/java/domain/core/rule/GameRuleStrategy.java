package domain.core.rule;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.state.GameState;

/**
 * Stratégie principale pour la gestion des transitions d'état dans le jeu de tennis.
 * <p>
 * Cette interface définit le contrat pour toute règle métier permettant de déterminer
 * le prochain état du jeu (GameState) en fonction de l'état courant et du joueur gagnant.
 *
 */
public interface GameRuleStrategy {
    GameState nextState(GameState gameState, Player winner);
}
