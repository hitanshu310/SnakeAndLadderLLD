package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Connector {

    private int start;
    private int end;
    private ConnectorType connectorType;

    public Connector(int start, int end, ConnectorType connectorType) {
        this.start = start;
        this.end = end;
        this.connectorType = connectorType;
    }
}
