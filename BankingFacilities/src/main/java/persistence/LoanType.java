package persistence;

import java.util.HashSet;
import java.util.Set;

public class LoanType {

    private long id;
    private String typeName;
    private int interestRate;
    private Set grantConditions;

    public LoanType(String typeName, int interestRate) {
        this.typeName = typeName;
        this.interestRate = interestRate;
    }

    public LoanType(String typeName, int interestRate, HashSet grantConditions) {
        this.typeName = typeName;
        this.interestRate = interestRate;
        this.grantConditions = grantConditions;
    }

    public LoanType() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setGrantConditions(Set grantConditions) {
        this.grantConditions = grantConditions;
    }

    public String getTypeName() {
        return typeName;
    }

    public Set getGrantConditions() {
        return grantConditions;
    }

    public long getId() {
        return id;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public String toString() {
        return typeName;
    }
}

