package adapter.in.cli;

import domain.port.TennisGamePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleTennisGameRunnerInteractiveTest {
    private TennisGamePort gamePort;
    private PrintStream originalOut;
    private java.io.InputStream originalIn;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        gamePort = mock(TennisGamePort.class);
        originalOut = System.out;
        originalIn = System.in;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testRun_interactive() {
        String simulatedInput = "AAB\n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        when(gamePort.play("AAB", 'A', 'B')).thenReturn(List.of("0-0", "15-0", "30-0", "30-15"));
        ConsoleTennisGameRunner runner = new ConsoleTennisGameRunner(gamePort, System.out::println);
        runner.run();
        String output = outContent.toString();
        assertTrue(output.contains("15-0"));
        assertTrue(output.contains("30-0"));
        assertTrue(output.contains("30-15"));
    }

    @Test
    void testRun_interactive_emptyInput() {
        String simulatedInput = "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ConsoleTennisGameRunner runner = new ConsoleTennisGameRunner(gamePort, System.out::println);
        runner.run();
        String output = outContent.toString();
        assertTrue(output.contains("Aucune chaine de jeu fournie."));
    }

    @Test
    void testRun_interactive_invalidChars() {
        String simulatedInput = "ABC\n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ConsoleTennisGameRunner runner = new ConsoleTennisGameRunner(gamePort, System.out::println);
        runner.run();
        String output = outContent.toString();
        assertTrue(output.contains("Erreur dans le nombre de lettres distinctes."));
    }

    @Test
    void testRun_interactive_onePlayerDetected() {
        String simulatedInput = "AAAA\n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        when(gamePort.play("AAAA", 'A', 'B')).thenReturn(List.of("0-0", "15-0", "30-0", "40-0"));
        ConsoleTennisGameRunner runner = new ConsoleTennisGameRunner(gamePort, System.out::println);
        runner.run();
        String output = outContent.toString();
        assertTrue(output.contains("Un seul joueurA detecté, l'autre joueur sera B"));
        assertTrue(output.contains("40-0"));
    }
}
