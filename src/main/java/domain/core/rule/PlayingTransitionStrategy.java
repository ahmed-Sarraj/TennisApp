package domain.core.rule;

import domain.core.model.Player;
import domain.core.rule.playing.PlayingRule;
import domain.core.state.GameState;
import domain.core.state.Playing;

import java.util.List;

/**
 * Stratégie de transition pour l'état Playing dans le jeu de tennis.
 * <p>
 * Cette classe gère la logique métier permettant de passer d'un état Playing à un autre état
 * (Playing, Deuce, Win, etc.) en fonction du score mis à jour et des règles métier (PlayingRule).
 * <ul>
 *   <li>Incrémente le score du joueur gagnant via ScoreRule.</li>
 *   <li>Applique la première règle de transition (PlayingRule) qui s'applique après l'incrément.</li>
 *   <li>Retourne l'état résultant (Win, Deuce, ou Playing mis à jour).</li>
 * </ul>
 */

public class PlayingTransitionStrategy implements TransitionStrategy {

    private final ScoreRule scoreRule;
    private final List<PlayingRule> rules ;

    public PlayingTransitionStrategy(ScoreRule scoreRule, List<PlayingRule> rules) {
        this.scoreRule = scoreRule;
        this.rules = rules;
    }

    @Override
    public GameState transition(GameState state, Player winner) {
        Playing playing = (Playing) state;
        Playing updatedPlaying = new Playing(
                scoreRule.update(winner, playing.server()),
                scoreRule.update(winner, playing.receiver())
        );
        return rules.stream()
                .filter(rule -> rule.accept(playing,updatedPlaying, winner))
                .findFirst()
                .map(rule -> rule.apply(updatedPlaying, winner))
                .orElse(updatedPlaying);
    }
}
