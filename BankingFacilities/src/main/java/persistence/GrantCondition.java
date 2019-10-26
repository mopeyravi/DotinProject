package persistence;

public class GrantCondition {
    private int conditionID;
    private String name;
    private Long minAgreementAmount;
    private Long maxAgreementAmount;
    private int minAgreementTime;
    private int maxAgreementTime;

    public GrantCondition() {
    }

    public GrantCondition( String name, Long minAgreementAmount, Long maxAgreementAmount, int minAgreementTime, int maxAgreementTime) {
        this.name = name;
        this.minAgreementAmount = minAgreementAmount;
        this.maxAgreementAmount = maxAgreementAmount;
        this.minAgreementTime = minAgreementTime;
        this.maxAgreementTime = maxAgreementTime;
    }

    public GrantCondition(Long minAgreementAmount, Long maxAgreementAmount, int minAgreementTime, int maxAgreementTime) {
        this.minAgreementAmount = minAgreementAmount;
        this.maxAgreementAmount = maxAgreementAmount;
        this.minAgreementTime = minAgreementTime;
        this.maxAgreementTime = maxAgreementTime;
    }

    public String getName() {
        return name;
    }

    public int getConditionID() {
        return conditionID;
    }

    public Long getMaxAgreementAmount() {
        return maxAgreementAmount;
    }

    public Long getMinAgreementAmount() {
        return minAgreementAmount;
    }

    public int getMinAgreementTime() {
        return minAgreementTime;
    }

    public int getMaxAgreementTime() {
        return maxAgreementTime;
    }

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }

    public void setMinAgreementAmount(Long minAgreementAmount) {
        this.minAgreementAmount = minAgreementAmount;
    }

    public void setMaxAgreementAmount(Long maxAgreementAmount) {
        this.maxAgreementAmount = maxAgreementAmount;
    }

    public void setMinAgreementTime(int minAgreementTime) {
        this.minAgreementTime = minAgreementTime;
    }

    public void setMaxAgreementTime(int maxAgreementTime) {
        this.maxAgreementTime = maxAgreementTime;
    }

    public void setName(String name) {
        this.name = name;
    }
}
