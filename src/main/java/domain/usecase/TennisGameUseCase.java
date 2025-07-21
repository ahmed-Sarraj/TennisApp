package domain.usecase;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import domain.core.rule.GameRuleStrategy;
import domain.core.state.GameState;
import domain.core.state.Playing;
import domain.port.TennisGamePort;

import java.util.ArrayList;
import java.util.List;

/**
 * Cas d'utilisation principal pour exécuter une partie de tennis.
 * <p>
 * Orchestration de la logique métier : reçoit une séquence de points, met à jour l'état du jeu
 * à chaque point selon les règles métier, et retourne l'évolution du score.
 */

public class TennisGameUseCase implements TennisGamePort {
    private final GameRuleStrategy gameRule;

    public TennisGameUseCase(GameRuleStrategy gameRule) {
        this.gameRule = gameRule;
    }


    @Override
    public List<String> play(String input, char player1, char player2) {
        Player firstPlayer = new Player(player1);
        Player secondPlayer = new Player(player2);
        GameState initialState = new Playing(new PlayerScore(firstPlayer, ScoreValue.LOVE),
                new PlayerScore(secondPlayer, ScoreValue.LOVE));
        List<String> history = new ArrayList<>();
        history.add(initialState.score());
        input.chars().mapToObj(c -> (char) c)
                .reduce(initialState, (state, c) -> {
                    Player winner = (c == player1) ? firstPlayer : secondPlayer;
                    GameState nextState = gameRule.nextState(state, winner);
                    history.add(nextState.score());
                    return nextState;
                }, (s1, s2) -> s1); // This is a no-op for sequential streams

        return history;
    }
}
