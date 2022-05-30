package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    Dice dice;

    @BeforeEach
    void setup(){
        dice = new Dice((int)(Math.random() * 6) + 1);
    }

    @Test
    @DisplayName("Dice Range Test")
    void testDiceRange() {
        for (int i = 0; i < 5; i++)
        {
            int rolled = dice.roll();
            System.out.println("Rolling " + dice.getNumberOfDice() + " Die/dice");
            System.out.println(rolled + " was rolled");
            assertTrue(rolled <= dice.getNumberOfDice()*6);
            assertTrue(rolled >= dice.getNumberOfDice());
        }
    }
}