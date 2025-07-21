package domain.core.rule;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicScoreRuleTest {
    private final ClassicScoreRule rule = new ClassicScoreRule();
    private final Player player = new Player('A');

    @Test
    void testUpdateFromLove() {
        PlayerScore score = new PlayerScore(player, ScoreValue.LOVE);
        PlayerScore updated = rule.update(player, score);
        assertEquals(ScoreValue.FIFTEEN, updated.score());
    }

    @Test
    void testUpdateFromFifteen() {
        PlayerScore score = new PlayerScore(player, ScoreValue.FIFTEEN);
        PlayerScore updated = rule.update(player, score);
        assertEquals(ScoreValue.THIRTY, updated.score());
    }

    @Test
    void testUpdateFromThirty() {
        PlayerScore score = new PlayerScore(player, ScoreValue.THIRTY);
        PlayerScore updated = rule.update(player, score);
        assertEquals(ScoreValue.FORTY, updated.score());
    }

    @Test
    void testUpdateFromForty() {
        PlayerScore score = new PlayerScore(player, ScoreValue.FORTY);
        PlayerScore updated = rule.update(player, score);
        assertEquals(ScoreValue.FORTY, updated.score());
    }

    @Test
    void testUpdateOtherPlayer() {
        Player other = new Player('B');
        PlayerScore score = new PlayerScore(other, ScoreValue.LOVE);
        PlayerScore updated = rule.update(player, score);
        assertEquals(ScoreValue.LOVE, updated.score());
    }
}

