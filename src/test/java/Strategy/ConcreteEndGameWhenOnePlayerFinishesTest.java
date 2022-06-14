package Strategy;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteEndGameWhenOnePlayerFinishesTest {

    private GameCompletionStrategy gameCompletionStrategy;

    @BeforeEach
    public void setup(){
        gameCompletionStrategy = new ConcreteEndGameWhenOnePlayerFinishes();
    }

    @Test
    @DisplayName("One player finishes the game among 2 players")
    public void onePlayerFinishesInGameOfTwo(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<Player>();
        Player p1 = new Player("A");
        p1.setFinished(true);
        Player p2 = new Player("B");
        playerQueue.add(p2);
        assertTrue(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

    @Test
    @DisplayName("No player finishes the game among 2 players")
    public void noPlayerFinishesInGameOfTwo(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<Player>();
        Player p1 = new Player("A");
        Player p2 = new Player("B");
        playerQueue.add(p2);
        assertFalse(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

    @Test
    @DisplayName("No player finishes the game among multiple players")
    public void noPlayerFinishesInGameOfMany(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<Player>();
        Player p1 = new Player("A");
        Player p2 = new Player("B");
        Player p3 = new Player("C");
        playerQueue.add(p2);
        playerQueue.add(p3);
        assertFalse(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

    @Test
    @DisplayName("One player finishes the game among multiple players")
    public void onePlayerFinishesInGameOfMany(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<Player>();
        Player p1 = new Player("A");
        p1.getFinished();
        Player p2 = new Player("B");
        Player p3 = new Player("C");
        playerQueue.add(p2);
        playerQueue.add(p3);
        assertFalse(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

}