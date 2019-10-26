import java.io.Serializable;

public class TerminalRequest implements Serializable {
    private String terminalID;
    private Transaction transaction;

    public TerminalRequest(Transaction trans, String terminalID) {
        this.transaction = trans;
        this.terminalID = terminalID;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getTerminalID() {
        return terminalID;
    }
}
