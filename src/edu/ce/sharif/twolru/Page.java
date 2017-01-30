package edu.ce.sharif.twolru;

import java.math.BigInteger;

/**
 * Created by mohammad on 1/30/17.
 */
public class Page implements Comparable<Page>{
    private BigInteger address;
    private Long accessNum;

    @Override
    public int compareTo(Page o) {
        if (this.accessNum > o.getAccessNum())
            return 1;
        else if(this.accessNum == o.getAccessNum())
            return 0;
        else
            return -1;
    }

    public Page(BigInteger address){
        this.address=address;
        this.accessNum=1L;
    }

    @Override
    public boolean equals(Object obj) {
        Page toComapare=(Page)obj;
        if(toComapare.getAddress().equals(this.getAddress()))
            return true;
        return false;
    }

    public BigInteger getAddress() {
        return address;
    }

    public void setAddress(BigInteger address) {
        this.address = address;
    }

    public Long getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Long accessNum) {
        this.accessNum = accessNum;
    }
}
