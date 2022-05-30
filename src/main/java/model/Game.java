package model;

import Strategy.GameInitStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.Board;
import model.Player;

import java.security.cert.CertPathValidatorSpi;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

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

