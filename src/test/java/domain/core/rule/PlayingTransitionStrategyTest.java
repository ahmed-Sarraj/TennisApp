package domain.core.rule;

import domain.core.model.Player;
import domain.core.model.PlayerScore;
import domain.core.rule.playing.PlayingRule;
import domain.core.state.GameState;
import domain.core.state.Playing;
import domain.core.state.Advantage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayingTransitionStrategyTest {
    private ScoreRule scoreRule;
    private PlayingRule playingRule1;
    private PlayingRule playingRule2;
    private Player winner;
    private PlayerScore serverScore;
    private PlayerScore receiverScore;
    private PlayerScore updatedServerScore;
    private PlayerScore updatedReceiverScore;

    @BeforeEach
    void setUp() {
        scoreRule = mock(ScoreRule.class);
        playingRule1 = mock(PlayingRule.class);
        playingRule2 = mock(PlayingRule.class);
        winner = mock(Player.class);
        serverScore = mock(PlayerScore.class);
        receiverScore = mock(PlayerScore.class);
        updatedServerScore = mock(PlayerScore.class);
        updatedReceiverScore = mock(PlayerScore.class);
    }

    @Test
    void testTransition_AppliesFirstAcceptedRule() {
        Playing playing = new Playing(serverScore, receiverScore);
        Playing updatedPlaying = new Playing(updatedServerScore, updatedReceiverScore);
        GameState expectedState = new Advantage(winner); // Utilisation d'une implémentation concrète
        List<PlayingRule> rules = List.of(playingRule1, playingRule2);

        when(scoreRule.update(winner, serverScore)).thenReturn(updatedServerScore);
        when(scoreRule.update(winner, receiverScore)).thenReturn(updatedReceiverScore);
        when(playingRule1.accept(playing, updatedPlaying, winner)).thenReturn(true);
        when(playingRule1.apply(updatedPlaying, winner)).thenReturn(expectedState);

        PlayingTransitionStrategy strategy = new PlayingTransitionStrategy(scoreRule, rules);
        GameState result = strategy.transition(playing, winner);

        verify(playingRule1).accept(playing, updatedPlaying, winner);
        verify(playingRule1).apply(updatedPlaying, winner);
        verifyNoInteractions(playingRule2);
        assertEquals(expectedState, result);
    }

    @Test
    void testTransition_SecondRuleAccepted() {
        Playing playing = new Playing(serverScore, receiverScore);
        Playing updatedPlaying = new Playing(updatedServerScore, updatedReceiverScore);
        GameState expectedState = new Advantage(winner);
        List<PlayingRule> rules = List.of(playingRule1, playingRule2);

        when(scoreRule.update(winner, serverScore)).thenReturn(updatedServerScore);
        when(scoreRule.update(winner, receiverScore)).thenReturn(updatedReceiverScore);
        when(playingRule1.accept(playing, updatedPlaying, winner)).thenReturn(false);
        when(playingRule2.accept(playing, updatedPlaying, winner)).thenReturn(true);
        when(playingRule2.apply(updatedPlaying, winner)).thenReturn(expectedState);

        PlayingTransitionStrategy strategy = new PlayingTransitionStrategy(scoreRule, rules);
        GameState result = strategy.transition(playing, winner);

        verify(playingRule1).accept(playing, updatedPlaying, winner);
        verify(playingRule2).accept(playing, updatedPlaying, winner);
        verify(playingRule2).apply(updatedPlaying, winner);
        assertEquals(expectedState, result);
    }

    @Test
    void testTransition_NoRuleAccepted_ReturnsUpdatedPlaying() {
        Playing playing = new Playing(serverScore, receiverScore);
        Playing updatedPlaying = new Playing(updatedServerScore, updatedReceiverScore);
        List<PlayingRule> rules = List.of(playingRule1, playingRule2);

        when(scoreRule.update(winner, serverScore)).thenReturn(updatedServerScore);
        when(scoreRule.update(winner, receiverScore)).thenReturn(updatedReceiverScore);
        when(playingRule1.accept(playing, updatedPlaying, winner)).thenReturn(false);
        when(playingRule2.accept(playing, updatedPlaying, winner)).thenReturn(false);

        PlayingTransitionStrategy strategy = new PlayingTransitionStrategy(scoreRule, rules);
        GameState result = strategy.transition(playing, winner);

        verify(playingRule1).accept(playing, updatedPlaying, winner);
        verify(playingRule2).accept(playing, updatedPlaying, winner);
        assertEquals(updatedPlaying, result);
    }

    @Test
    void testTransition_WithEmptyRules_ReturnsUpdatedPlaying() {
        Playing playing = new Playing(serverScore, receiverScore);
        Playing updatedPlaying = new Playing(updatedServerScore, updatedReceiverScore);
        List<PlayingRule> rules = List.of();

        when(scoreRule.update(winner, serverScore)).thenReturn(updatedServerScore);
        when(scoreRule.update(winner, receiverScore)).thenReturn(updatedReceiverScore);

        PlayingTransitionStrategy strategy = new PlayingTransitionStrategy(scoreRule, rules);
        GameState result = strategy.transition(playing, winner);

        assertEquals(updatedPlaying, result);
    }
}
