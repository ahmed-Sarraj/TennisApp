package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.Advantage;
import domain.core.state.Deuce;
import domain.core.state.GameState;
import domain.core.state.Win;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvantageTransitionStrategyTest {
    private final Player advantaged = new Player('A');
    private final Player opponent = new Player('B');
    private final AdvantageTransitionStrategy strategy = new AdvantageTransitionStrategy();

    @Test
    void testTransition_WinnerIsAdvantaged_ReturnsWin() {
        Advantage state = new Advantage(advantaged);
        GameState result = strategy.transition(state, advantaged);
        assertInstanceOf(Win.class, result);
        assertEquals(advantaged, ((Win) result).winner());
    }

    @Test
    void testTransition_WinnerIsOpponent_ReturnsDeuce() {
        Advantage state = new Advantage(advantaged);
        GameState result = strategy.transition(state, opponent);
        assertInstanceOf(Deuce.class, result);
    }
}
