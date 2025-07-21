package domain.core.state;

import domain.core.model.PlayerScore;


/**
 * État Playing dans le jeu de tennis.
 */
public record Playing(PlayerScore server, PlayerScore receiver) implements GameState {


    @Override
    public String score() {
        return "Player" + server.player().name() +" : "+server.display() + " / " + "Player" + server.player().name() +" : "+ receiver.display();
    }


}
