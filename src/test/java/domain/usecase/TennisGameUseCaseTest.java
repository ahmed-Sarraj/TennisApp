package domain.usecase;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import domain.core.rule.GameRuleStrategy;
import domain.core.state.GameState;
import domain.core.state.Playing;
import domain.port.TennisGamePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TennisGameUseCaseTest {
    private GameRuleStrategy gameRule;
    private TennisGameUseCase useCase;

    @BeforeEach
    void setUp() {
        gameRule = mock(GameRuleStrategy.class);
        useCase = new TennisGameUseCase(gameRule);
    }

    @Test
    void play_returnsInitialScoreAndHistory() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "";
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(1, result.size());
        assertTrue(result.getFirst().contains("0"));
    }

    @Test
    void play_updatesScoreForEachPoint() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "AB";
        Player playerA = new Player(p1);
        Player playerB = new Player(p2);
        GameState state1 = new Playing(new PlayerScore(playerA, ScoreValue.FIFTEEN), new PlayerScore(playerB, ScoreValue.LOVE));
        GameState state2 = new Playing(new PlayerScore(playerA, ScoreValue.FIFTEEN), new PlayerScore(playerB, ScoreValue.FIFTEEN));
        when(gameRule.nextState(any(), eq(playerA))).thenReturn(state1);
        when(gameRule.nextState(any(), eq(playerB))).thenReturn(state2);
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(3, result.size());
        assertTrue(result.get(1).contains("15"));
        assertTrue(result.get(2).contains("15"));
    }

    @Test
    void play_handlesEmptyInput() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "";
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(1, result.size());
        assertTrue(result.getFirst().contains("0"));
    }

    @Test
    void play_handlesSinglePointForPlayer1() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "A";
        Player playerA = new Player(p1);
        Player playerB = new Player(p2);
        GameState state1 = new Playing(new PlayerScore(playerA, ScoreValue.FIFTEEN), new PlayerScore(playerB, ScoreValue.LOVE));
        when(gameRule.nextState(any(), eq(playerA))).thenReturn(state1);
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(2, result.size());
        assertTrue(result.get(1).contains("15"));
    }

    @Test
    void play_handlesSinglePointForPlayer2() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "B";
        Player playerA = new Player(p1);
        Player playerB = new Player(p2);
        GameState state1 = new Playing(new PlayerScore(playerA, ScoreValue.LOVE), new PlayerScore(playerB, ScoreValue.FIFTEEN));
        when(gameRule.nextState(any(), eq(playerB))).thenReturn(state1);
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(2, result.size());
        assertTrue(result.get(1).contains("15"));
    }

    @Test
    void play_handlesMultiplePointsAndHistory() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "AAB";
        Player playerA = new Player(p1);
        Player playerB = new Player(p2);
        GameState state1 = new Playing(new PlayerScore(playerA, ScoreValue.FIFTEEN), new PlayerScore(playerB, ScoreValue.LOVE));
        GameState state2 = new Playing(new PlayerScore(playerA, ScoreValue.THIRTY), new PlayerScore(playerB, ScoreValue.LOVE));
        GameState state3 = new Playing(new PlayerScore(playerA, ScoreValue.THIRTY), new PlayerScore(playerB, ScoreValue.FIFTEEN));
        when(gameRule.nextState(any(), eq(playerA))).thenReturn(state1, state2);
        when(gameRule.nextState(any(), eq(playerB))).thenReturn(state3);
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(4, result.size());
        assertTrue(result.get(1).contains("15"));
        assertTrue(result.get(2).contains("30"));
        assertTrue(result.get(3).contains("15"));
    }

    @Test
    void play_throwsExceptionForInvalidInput() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "C"; // ni A ni B
        assertThrows(Exception.class, () -> useCase.play(input, p1, p2));
    }

    @Test
    void play_handlesWinState() {
        char p1 = 'A';
        char p2 = 'B';
        String input = "A";
        Player playerA = new Player(p1);
        GameState winState = new domain.core.state.Win(playerA);
        when(gameRule.nextState(any(), eq(playerA))).thenReturn(winState);
        List<String> result = useCase.play(input, p1, p2);
        assertEquals(2, result.size());
        assertTrue(result.get(1).contains("WIN") || result.get(1).contains("win") || result.get(1).contains("Win"));
    }
}
