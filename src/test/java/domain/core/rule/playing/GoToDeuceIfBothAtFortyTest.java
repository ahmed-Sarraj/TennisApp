package domain.core.rule.playing;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.model.ScoreValue;
import domain.core.state.Deuce;
import domain.core.state.GameState;
import domain.core.state.Playing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoToDeuceIfBothAtFortyTest {
    @Test
    void accept_returnsTrue_whenBothAtForty() {
        PlayerScore serverScore = mock(PlayerScore.class);
        PlayerScore receiverScore = mock(PlayerScore.class);
        when(serverScore.score()).thenReturn(ScoreValue.FORTY);
        when(receiverScore.score()).thenReturn(ScoreValue.FORTY);
        Playing updatedPlaying = new Playing(serverScore, receiverScore);
        GoToDeuceIfBothAtForty rule = new GoToDeuceIfBothAtForty();
        boolean result = rule.accept(null, updatedPlaying, mock(Player.class));
        assertTrue(result);
    }

    @Test
    void accept_returnsFalse_whenNotBothAtForty() {
        PlayerScore serverScore = mock(PlayerScore.class);
        PlayerScore receiverScore = mock(PlayerScore.class);
        when(serverScore.score()).thenReturn(ScoreValue.FORTY);
        when(receiverScore.score()).thenReturn(ScoreValue.THIRTY);
        Playing updatedPlaying = new Playing(serverScore, receiverScore);
        GoToDeuceIfBothAtForty rule = new GoToDeuceIfBothAtForty();
        boolean result = rule.accept(null, updatedPlaying, mock(Player.class));
        assertFalse(result);
    }

    @Test
    void apply_returnsDeuceState() {
        Player winner = mock(Player.class);
        PlayerScore serverScore = mock(PlayerScore.class);
        PlayerScore receiverScore = mock(PlayerScore.class);
        Playing playing = new Playing(serverScore, receiverScore);
        GoToDeuceIfBothAtForty rule = new GoToDeuceIfBothAtForty();
        GameState result = rule.apply(playing, winner);
        assertInstanceOf(Deuce.class, result);
    }
}

