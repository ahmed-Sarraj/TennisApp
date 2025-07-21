package domain.core.rule.playing;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import domain.core.state.GameState;
import domain.core.state.Playing;
import domain.core.state.Win;

/**
 * Règle métier pour détecter la victoire directe d'un joueur à 40 points.
 * <p>
 * Cette règle s'applique lors d'une transition d'état Playing: si le score du gagnant passe de 30 à 40
 * et que l'adversaire n'est pas à 40, alors le joueur gagne la partie (état Win).
 */

public class WinIfOnlyWinnerAtForty implements PlayingRule {
    private PlayerScore winnerScore(Playing state, Player player) {
        return state.server().player().equals(player) ? state.server() : state.receiver();
    }

    private PlayerScore opponentScore(Playing state, Player player) {
        return state.server().player().equals(player) ? state.receiver() : state.server();


    }

    @Override
    public boolean accept(Playing state, Playing updatedState, Player winner) {
        PlayerScore winnerScoreBefore = winnerScore(state, winner);
        PlayerScore winnerScoreAfter = winnerScore(updatedState, winner);
        PlayerScore opponentScoreAfter = opponentScore(updatedState, winner);
        // Retourne true si le score du gagnant a deja 40 ET que l'adversaire n'est pas à 40
        return winnerScoreBefore.score().equals(ScoreValue.FORTY)
                && winnerScoreAfter.score().equals(ScoreValue.FORTY)
                && !opponentScoreAfter.score().equals(ScoreValue.FORTY);
    }

    @Override
    public GameState apply(Playing state, Player winner) {
        // Retourne l'état Win pour le joueur gagnant
        return new Win(winner);
    }
}
