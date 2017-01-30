package edu.ce.sharif.twolru;

import java.math.BigInteger;

/**
 * Created by mohammad on 1/30/17.
 */
public class Request {

    private int operation;
    private BigInteger address;
    private Long size;

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public BigInteger getAddress() {
        return address;
    }

    public void setAddress(BigInteger address) {
        this.address = address;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
