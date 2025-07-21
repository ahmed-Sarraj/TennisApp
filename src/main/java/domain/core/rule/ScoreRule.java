package domain.core.rule;

import domain.core.model.Player;
import domain.core.model.PlayerScore;

/**
 * Interface pour la règle de progression du score au tennis.
 * <p>
 * Permet de définir comment le score d'un joueur doit être mis à jour lorsqu'il gagne un point.
 * Utilisée par les stratégies de transition pour appliquer la logique métier de scoring.
 */

public interface ScoreRule {
    PlayerScore update(Player winner, PlayerScore current);
}
