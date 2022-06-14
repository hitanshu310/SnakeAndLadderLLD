package Strategy;

import model.Game;
import model.Player;

import java.util.Queue;

public interface GameCompletionStrategy {

    /* Given a player who has played a turn who is not a part of the queue and the remaining queue
    of players determine if the game is complete or not
     */
    public boolean isGameComplete(Player p1, Queue<Player> playerQueue);

}
