package domain.core.rule.playing;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import domain.core.state.Playing;
import domain.core.state.Win;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WinIfOnlyWinnerAtFortyTest {
    @Test
    void accept_returnsTrue_whenWinnerAtFortyAndOpponentNotAtForty() {
        Player winner = mock(Player.class);
        Player opponent = mock(Player.class);
        PlayerScore winnerScore = mock(PlayerScore.class);
        PlayerScore opponentScore = mock(PlayerScore.class);
        when(winnerScore.player()).thenReturn(winner);
        when(opponentScore.player()).thenReturn(opponent);
        when(winnerScore.score()).thenReturn(ScoreValue.FORTY);
        when(opponentScore.score()).thenReturn(ScoreValue.THIRTY);
        Playing updatedPlaying = new Playing(winnerScore, opponentScore);
        WinIfOnlyWinnerAtForty rule = new WinIfOnlyWinnerAtForty();
        boolean result = rule.accept(null, updatedPlaying, winner);
        assertTrue(result);
    }

    @Test
    void accept_returnsFalse_whenBothAtForty() {
        Player winner = mock(Player.class);
        Player opponent = mock(Player.class);
        PlayerScore winnerScore = mock(PlayerScore.class);
        PlayerScore opponentScore = mock(PlayerScore.class);
        when(winnerScore.player()).thenReturn(winner);
        when(opponentScore.player()).thenReturn(opponent);
        when(winnerScore.score()).thenReturn(ScoreValue.FORTY);
        when(opponentScore.score()).thenReturn(ScoreValue.FORTY);
        Playing updatedPlaying = new Playing(winnerScore, opponentScore);
        WinIfOnlyWinnerAtForty rule = new WinIfOnlyWinnerAtForty();
        boolean result = rule.accept(null, updatedPlaying, winner);
        assertFalse(result);
    }

    @Test
    void accept_returnsFalse_whenWinnerNotAtForty() {
        Player winner = mock(Player.class);
        Player opponent = mock(Player.class);
        PlayerScore winnerScore = mock(PlayerScore.class);
        PlayerScore opponentScore = mock(PlayerScore.class);
        when(winnerScore.player()).thenReturn(winner);
        when(opponentScore.player()).thenReturn(opponent);
        when(winnerScore.score()).thenReturn(ScoreValue.THIRTY);
        when(opponentScore.score()).thenReturn(ScoreValue.FORTY);
        Playing updatedPlaying = new Playing(winnerScore, opponentScore);
        WinIfOnlyWinnerAtForty rule = new WinIfOnlyWinnerAtForty();
        boolean result = rule.accept(null, updatedPlaying, winner);
        assertFalse(result);
    }

    @Test
    void apply_returnsWinState() {
        Player winner = mock(Player.class);
        PlayerScore winnerScore = mock(PlayerScore.class);
        PlayerScore opponentScore = mock(PlayerScore.class);
        when(winnerScore.player()).thenReturn(winner);
        Playing updatedPlaying = new Playing(winnerScore, opponentScore);
        WinIfOnlyWinnerAtForty rule = new WinIfOnlyWinnerAtForty();
        var result = rule.apply(updatedPlaying, winner);
        assertInstanceOf(Win.class, result);
        assertEquals(winner, ((Win) result).winner());
    }
}

