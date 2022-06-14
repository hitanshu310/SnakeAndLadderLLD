package model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Player {

    private final String playerName;

    private int location = 0;

    private boolean finished;

    public Player(String playerName) {
        this.playerName = playerName;
        this.finished = false;
    }

    public boolean getFinished(){
        return this.finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

}
