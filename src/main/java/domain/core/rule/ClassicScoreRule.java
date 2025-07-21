package domain.core.rule;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;

/**
 * Implémentation classique de la règle de progression du score au tennis.
 * <p>
 * Cette classe gère l'incrément du score d'un joueur lorsqu'il gagne un point.
 * Elle respecte la logique du tennis : LOVE → FIFTEEN → THIRTY → FORTY.
 * Si le joueur est déjà à FORTY, son score ne change plus.
 * <p>
 */

public class ClassicScoreRule implements ScoreRule {

    @Override
    public PlayerScore update(Player winner, PlayerScore current) {
        if (current.player().equals(winner) && current.score() != ScoreValue.FORTY) {
            return new PlayerScore(winner, next(current.score()));
        }
        return current;
    }

    private ScoreValue next(ScoreValue current) {
        return switch (current) {
            case LOVE -> ScoreValue.FIFTEEN;
            case FIFTEEN -> ScoreValue.THIRTY;
            case THIRTY, FORTY -> ScoreValue.FORTY;
        };
    }
}
