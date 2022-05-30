package model;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {

    private final String playerName;

    @Setter
    private int location = 0;

    @Setter
    private boolean won = false;

    public Player(String playerName) {
        this.playerName = playerName;
    }
}
