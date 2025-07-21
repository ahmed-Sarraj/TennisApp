package domain.core.model;

import domain.core.rule.ScoreRule;

/**
 * Agrégat métier représentant un joueur et son score.
 */
public record PlayerScore(Player player, ScoreValue score) {


    /**
     * Affiche le score du joueur sous forme de texte.
     */
    public String display() {
        return player.name() + " : " + score.display();
    }


}
