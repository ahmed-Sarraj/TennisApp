package domain.core.state;

/**
 * Interface scellant les différents états possibles d'une partie de tennis.
 */
public sealed interface GameState permits Playing, Deuce, Advantage, Win {
    String score();
}
