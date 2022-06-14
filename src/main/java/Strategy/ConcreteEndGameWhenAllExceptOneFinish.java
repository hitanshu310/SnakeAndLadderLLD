package Strategy;

import model.Player;

import java.util.Queue;

public class ConcreteEndGameWhenAllExceptOneFinish implements GameCompletionStrategy {
    @Override
    public boolean isGameComplete(Player p1, Queue<Player> playerQueue) {
        if (p1.getFinished() && playerQueue.size() == 1)
            return true;
        else
            return false;
    }
}
