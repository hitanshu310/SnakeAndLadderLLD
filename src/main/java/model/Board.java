package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Board {
    private int size;

    public Board (int size){
        this.size = size;
    }

}


