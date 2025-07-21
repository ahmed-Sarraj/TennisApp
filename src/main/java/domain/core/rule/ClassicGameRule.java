package domain.core.rule;

import domain.core.model.Player;
import domain.core.state.GameState;

import java.util.Map;

/**
 * Implémentation classique de la stratégie de règles métier pour le tennis.
 * <p>
 * Cette classe centralise la gestion des transitions d'état du jeu en fonction de l'état courant
 * et du joueur gagnant, en déléguant la logique à la bonne TransitionStrategy selon le type d'état.
 * <ul>
 *   <li>Utilise une map pour associer chaque classe d'état à sa stratégie de transition.</li>
 * </ul>
 */
public final class ClassicGameRule implements GameRuleStrategy {
    private final Map<Class<? extends GameState>, TransitionStrategy> transitions;


    public ClassicGameRule(Map<Class<? extends GameState>, TransitionStrategy> transitions) {
        this.transitions = transitions;
    }


    @Override
    public GameState nextState(GameState gameState, Player winner) {
        return transitions.get(gameState.getClass()).transition(gameState, winner);
    }
}
