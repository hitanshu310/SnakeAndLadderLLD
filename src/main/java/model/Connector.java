package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Connector {

    private int start;
    private int end;
    private ConnectorType connectorType;

    public Connector(int start, int end, ConnectorType connectorType) {
        this.start = start;
        this.end = end;
        this.connectorType = connectorType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ConnectorType getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(ConnectorType connectorType) {
        this.connectorType = connectorType;
    }
}
