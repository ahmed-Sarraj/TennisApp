package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.GameState;

/**
 * Interface pour les stratégies de transition d'état dans le jeu de tennis.
 * <p>
 * Permet de définir la logique de passage d'un état du jeu (GameState) à un autre
 * en fonction du joueur gagnant. Utilisée pour modulariser et séparer la logique métier
 * de chaque type d'état (Playing, Deuce, Advantage, etc.).
 */
public interface TransitionStrategy {
    GameState transition(GameState currentState, Player winner);
}
