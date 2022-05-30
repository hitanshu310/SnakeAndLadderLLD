package Validators;

import model.Connector;
import model.ConnectorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BasicSnakeLadderValidatorTest {

    private IValidate validator;

    @BeforeEach
    void setup(){
        validator = new BasicSnakeLadderValidator();
    }

    @Test
    @DisplayName("Snake where end is greater than start")
    public void snakeEndGreaterThanStart(){
        Connector snake = new Connector(3, 5, ConnectorType.SNAKE);
        System.out.println("Snake start : " + snake.getStart() + " Snake end : " + snake.getEnd());
        assertFalse(this.validator.validate(snake, null, null));
    }

    @Test
    @DisplayName("Snake where start is greater than end")
    public void snakeStartGreaterThanEnd(){
        Connector snake = new Connector(78, 21, ConnectorType.SNAKE);
        System.out.println("Snake start : " + snake.getStart() + " Snake end : " + snake.getEnd());
        assertTrue(this.validator.validate(snake, null, null));
    }

    @Test
    @DisplayName("Snake where end is greater than start")
    public void ladderEndGreaterThanStart(){
        Connector ladder = new Connector(3, 5, ConnectorType.LADDER);
        System.out.println("Snake start : " + ladder.getStart() + " Snake end : " + ladder.getEnd());
        assertTrue(this.validator.validate(ladder, null, null));
    }

    @Test
    @DisplayName("Snake where start is greater than end")
    public void ladderStartGreaterThanEnd(){
        Connector ladder = new Connector(78, 21, ConnectorType.LADDER);
        System.out.println("Snake start : " + ladder.getStart() + " Snake end : " + ladder.getEnd());
        assertFalse(this.validator.validate(ladder, null, null));
    }

    @Test
    @DisplayName("Test validator chain of responsibility")
    public void testValidatorChainOfResponsibility(){
        IValidate iValidate = new IValidate() {
            @Override
            public boolean validate(Connector connector, HashMap<Integer, Connector> snakes, HashMap<Integer, Connector> ladders) {
                return false;
            }
        };
        Connector ladder = new Connector(3, 5, ConnectorType.LADDER);
        Connector snake = new Connector(78, 21, ConnectorType.SNAKE);
        this.validator.setValidator(iValidate);
        assertFalse(this.validator.validate(ladder, null, null));
        assertFalse(this.validator.validate(snake, null, null));
    }

}