package Builder;
import model.*;
import java.util.HashMap;
import java.util.Queue;

public class GameBuilder {
    private Board gameBoard;

    private int numberOfPlayers;

    private Queue<Player> playerQueue;

    private Dice dice;

    private HashMap<Integer, Connector> snakes;

    private HashMap<Integer, Connector> ladders;

    private int numSnakes = 5;

    private int numLadders = 5;

    public int getNumSnakes() {
        return numSnakes;
    }

    public int getNumLadders(){
        return numLadders;
    }

    public int getBoardSize(){
        return gameBoard.getSize();
    }

    public Game getGameInstance(){
        return new Game(this.gameBoard,this.numberOfPlayers, this.playerQueue, this.dice, this.snakes, this.ladders, this.numSnakes, this.numLadders);
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setPlayerQueue(Queue<Player> playerQueue) {
        this.playerQueue = playerQueue;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void setSnakes(HashMap<Integer, Connector> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(HashMap<Integer, Connector> ladders) {
        this.ladders = ladders;
    }
    public void setNumberOfLadders(int numLadders) {
        this.numLadders = numLadders;
    }
    public void setNumberOfSnakes(int numSnakes) {
        this.numSnakes = numSnakes;
    }
}
