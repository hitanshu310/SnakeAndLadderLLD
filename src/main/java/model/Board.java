package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Board {
    private int size;

    public Board (int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

}


