package domain.core.rule.playing;

import domain.core.model.Player;
import domain.core.model.ScoreValue;
import domain.core.state.Deuce;
import domain.core.state.GameState;
import domain.core.state.Playing;

/**
 * Règle métier pour la transition vers l'état Deuce.
 * <p>
 * Cette règle s'applique lorsque les deux joueurs atteignent 40 points.
 * Elle permet de passer de l'état Playing à l'état Deuce dans le jeu de tennis.
 */

public class GoToDeuceIfBothAtForty implements PlayingRule{
    @Override
    public boolean accept(Playing state,Playing updatedState, Player winner) {
        // Retourne vrai si les deux joueurs sont à 40
        return updatedState.server().score().equals(ScoreValue.FORTY)
                && updatedState.receiver().score().equals(ScoreValue.FORTY);
    }

    @Override
    public GameState apply(Playing state, Player winner) {
        // Passe à l'état Deuce avec les deux joueurs
        return new Deuce();
    }
}
