package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.Win;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinTransitionStrategyTest {
    private final Player playerA = new Player('A');
    private final WinTransitionStrategy strategy = new WinTransitionStrategy();

    @Test
    void testTransition_AlwaysReturnsSameWinState() {
        Win win = new Win(playerA);
        assertSame(win, strategy.transition(win, playerA));
        assertSame(win, strategy.transition(win, new Player('B')));
    }
}

