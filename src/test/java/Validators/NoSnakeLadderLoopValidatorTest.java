package Validators;

import model.Connector;
import model.ConnectorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NoSnakeLadderLoopValidatorTest {

    private IValidate validator;

    @BeforeEach
    void setup(){
        validator = new NoSnakeLadderLoopValidator();
    }

    @DisplayName("Snake Ladder loop test 1")
    @Test
    public void testSnakeLadderLoop1(){
        Connector snake1 = new Connector(45, 24, ConnectorType.SNAKE);
        HashMap<Integer, Connector> snakes = new HashMap<>();
        snakes.put(snake1.getStart(), snake1);
        Connector ladder1 = new Connector(24, 45, ConnectorType.LADDER);
        assertFalse(validator.validate(ladder1, snakes, new HashMap<>()));
    }

    @DisplayName("Snake Ladder loop test 1")
    @Test
    public void testSnakeLadderLoop2(){
        Connector ladder1 = new Connector(24, 45, ConnectorType.LADDER);
        HashMap<Integer, Connector> ladders = new HashMap<>();
        ladders.put(ladder1.getStart(), ladder1);
        Connector snake1 = new Connector(45, 24, ConnectorType.SNAKE);
        assertFalse(validator.validate(snake1, new HashMap<>(), ladders));
    }

    @DisplayName("Test complex snake ladder loop")
    @Test
    public void testSnakeAndLadderComplexLoop(){
        Connector ladder1 = new Connector(24, 49, ConnectorType.LADDER);
        Connector snake1 = new Connector(49, 37, ConnectorType.SNAKE);
        Connector ladder2 = new Connector(37, 53, ConnectorType.LADDER);
        Connector snake2 = new Connector(53, 38, ConnectorType.SNAKE);
        Connector snake3 = new Connector(38, 24, ConnectorType.SNAKE);
        HashMap<Integer, Connector> ladders = new HashMap<>();
        HashMap<Integer, Connector> snakes = new HashMap<>();
        snakes.put(snake1.getStart(), snake1);
        snakes.put(snake2.getStart(), snake2);
        ladders.put(ladder1.getStart(), ladder1);
        ladders.put(ladder2.getStart(), ladder2);
        assertFalse(validator.validate(snake3, snakes, ladders));
    }

    @DisplayName("Test validation success when no loop")
    @Test
    public void testSnakeAndLadderNoLoop(){
        Connector ladder1 = new Connector(24, 49, ConnectorType.LADDER);
        Connector snake1 = new Connector(49, 37, ConnectorType.SNAKE);
        Connector ladder2 = new Connector(37, 53, ConnectorType.LADDER);
        HashMap<Integer, Connector> ladders = new HashMap<>();
        HashMap<Integer, Connector> snakes = new HashMap<>();
        snakes.put(snake1.getStart(), snake1);
        ladders.put(ladder1.getStart(), ladder1);
        assertTrue(validator.validate(ladder2, snakes, ladders));
    }

    @DisplayName("Chain of Responsibility test")
    @Test
    public void testChainOfResponsibility(){
        IValidate iValidate = new IValidate() {
            @Override
            public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
                return false;
            }
        };
        validator.setValidator(iValidate);
        Connector ladder1 = new Connector(24, 49, ConnectorType.LADDER);
        Connector snake1 = new Connector(49, 37, ConnectorType.SNAKE);
        Connector ladder2 = new Connector(37, 53, ConnectorType.LADDER);
        HashMap<Integer, Connector> ladders = new HashMap<>();
        HashMap<Integer, Connector> snakes = new HashMap<>();
        snakes.put(snake1.getStart(), snake1);
        ladders.put(ladder1.getStart(), ladder1);
        assertFalse(validator.validate(ladder2, snakes, ladders));
    }


    @Test
    @DisplayName("Single connector validation test")
    public void singleConnectorValidationTest(){
        Connector snake1 = new Connector(78,44, ConnectorType.SNAKE);
        assertTrue(validator.validate(snake1, new HashMap<>(), new HashMap<>()));
    }


    @Test
    @DisplayName("Test validator chain of responsibility2")
    public void testValidatorChainOfResponsibility2(){
        IValidate iValidate = new IValidate() {
            @Override
            public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
                return true;
            }
        };
        Connector ladder = new Connector(3, 5, ConnectorType.LADDER);
        validator.setValidator(iValidate);
        assertTrue(validator.validate(ladder, new HashMap<>(), new HashMap<>()));
    }

}