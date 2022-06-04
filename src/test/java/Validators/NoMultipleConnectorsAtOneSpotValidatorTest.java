package Validators;

import model.Connector;
import model.ConnectorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NoMultipleConnectorsAtOneSpotValidatorTest {

    private IValidate validator;
    private HashMap<Integer, Connector> snakes;
    private HashMap<Integer, Connector> ladders;

    @BeforeEach
    void setup(){
        validator = new NoMultipleConnectorsAtOneSpotValidator();
        Connector snake = new Connector(48, 24, ConnectorType.SNAKE);
        Connector ladder = new Connector(12, 78, ConnectorType.LADDER);
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.snakes.put(snake.getStart(), snake);
        this.ladders.put(ladder.getStart(), ladder);
    }

    @Test
    @DisplayName("Two Snakes with Heads At Same Square Test")
    public void testTwoSnakesAtOneSpot(){
        Connector snake1 = new Connector(48,16, ConnectorType.SNAKE);
        assertFalse(validator.validate(snake1, this.snakes, this.ladders));
    }

    @Test
    @DisplayName("Two Ladders with bases at the same square")
    public void testTwoLaddersAtOneSpot(){
        Connector ladder1 = new Connector(12, 45, ConnectorType.LADDER);
        assertFalse(validator.validate(ladder1, snakes, ladders));
    }

    @Test
    @DisplayName("Ladder and Snake start at same square")
    public void testLadderAndSnakeAtOneSpot(){
        Connector snake1 = new Connector(12, 2, ConnectorType.SNAKE);
        Connector ladder1 = new Connector(48, 58, ConnectorType.LADDER);
        assertFalse(validator.validate(snake1, this.snakes, this.ladders));
        assertFalse(validator.validate(ladder1, this.snakes, this.ladders));
    }

    @Test
    @DisplayName("Ladder And Snake Start at Different Square")
    public void testLadderAndSnakeAtDifferentSquares(){
        Connector snake1 = new Connector(49, 12, ConnectorType.SNAKE);
        Connector ladder1 = new Connector(24,76, ConnectorType.LADDER);
        assertTrue(validator.validate(snake1, this.snakes, this.ladders));
        assertTrue(validator.validate(ladder1, this.snakes, this.ladders));
    }

    @Test
    @DisplayName("Chain of responsibility testing")
    public void chainOfResponsibilityTest(){
        IValidate iValidate = new IValidate() {
            @Override
            public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
                return false;
            }
        };
        Connector snake1 = new Connector(49, 12, ConnectorType.SNAKE);
        Connector ladder1 = new Connector(24,76, ConnectorType.LADDER);
        this.validator.setValidator(iValidate);
        assertFalse(validator.validate(snake1, this.snakes, this.ladders));
        assertFalse(validator.validate(ladder1, this.snakes, this.ladders));
    }

}