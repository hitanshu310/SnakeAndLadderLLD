package Strategy;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteEndGameWhenAllExceptOneFinishTest {

    private GameCompletionStrategy gameCompletionStrategy;

    @BeforeEach
    public void setup(){
        gameCompletionStrategy = new ConcreteEndGameWhenAllExceptOneFinish();
    }

    @Test
    @DisplayName("2 players play one finishes")
    public void twoPlayerPlayOneFinishes(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<>();
        Player p1 = new Player("A");
        p1.setFinished(true);
        Player p2 = new Player("B");
        playerQueue.add(p2);
        assertTrue(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

    @Test
    @DisplayName("Many players play one finishes")
    public void manyPlayersPlayOneFinishes(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<>();
        Player p1 = new Player("A");
        p1.setFinished(true);
        Player p2 = new Player("B");
        Player p3 = new Player ("C");
        playerQueue.add(p2);
        playerQueue.add(p3);
        assertFalse(gameCompletionStrategy.isGameComplete(p1, playerQueue));
        }

    @Test
    @DisplayName("Many players play no one finish")
    public void manyPlayersPlayNoOneFinish(){
        Queue<Player> playerQueue = new LinkedBlockingQueue<>();
        Player p1 = new Player("A");
        Player p2 = new Player("B");
        Player p3 = new Player ("C");
        playerQueue.add(p2);
        playerQueue.add(p3);
        assertFalse(gameCompletionStrategy.isGameComplete(p1, playerQueue));
    }

    }
