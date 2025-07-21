package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.Deuce;
import domain.core.state.GameState;
import domain.core.state.Advantage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeuceTransitionStrategyTest {
    private final Player playerA = new Player('A');
    private final Player playerB = new Player('B');
    private final DeuceTransitionStrategy strategy = new DeuceTransitionStrategy();

    @Test
    void testTransition_ReturnsAdvantageForWinner() {
        Deuce deuce = new Deuce();
        GameState resultA = strategy.transition(deuce, playerA);
        GameState resultB = strategy.transition(deuce, playerB);
        assertInstanceOf(Advantage.class, resultA);
        assertInstanceOf(Advantage.class, resultB);
        assertEquals(playerA, ((Advantage) resultA).advantaged());
        assertEquals(playerB, ((Advantage) resultB).advantaged());
    }
}

