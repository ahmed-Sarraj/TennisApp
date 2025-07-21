package domain.core.rule.playing;

import domain.core.model.Player;
import domain.core.state.GameState;
import domain.core.state.Playing;

/**
 * Interface pour les règles métier appliquées lors d'une transition d'état Playing.
 * <p>
 * Permet de définir des conditions (accept) et des actions (apply) spécifiques
 * pour chaque règle de transition dans le jeu de tennis.
 */
public interface PlayingRule {
    boolean accept(Playing state, Playing updatedState, Player winner);
    GameState apply(Playing state, Player winner);
}
