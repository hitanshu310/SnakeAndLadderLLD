package Strategy;

import model.Game;
import model.Player;

import java.util.Queue;

public class ConcreteEndGameWhenOnePlayerFinishes implements GameCompletionStrategy {

    @Override
    public boolean isGameComplete(Player p1, Queue<Player> playerQueue) {
        if(p1.getFinished())
            return true;
        else
            return false;
    }
}
