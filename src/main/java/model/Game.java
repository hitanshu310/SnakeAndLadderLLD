package model;
import lombok.Getter;
import java.util.HashMap;
import java.util.Queue;

@Getter
public class Game {

    // needs to be specified
    private Board gameBoard;
    private int numberOfPlayers;
    private Queue<Player> playerQueue;
    private Dice dice;
    private HashMap<Integer, Connector> snakes;
    private HashMap<Integer, Connector> ladders;
    private int numSnakes;
    private int numLadders;

    public Game(Board gameBoard, int numberOfPlayers, Queue<Player> playerQueue, Dice dice, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders, int numSnakes, int numLadders) {
    }
}

