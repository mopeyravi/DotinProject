import java.io.Serializable;


public class ServerResponse implements Serializable {
    private boolean doneCorrectly;
    private Transaction transaction;

    public ServerResponse(Transaction trans, boolean done) {
        this.transaction = trans;
        this.doneCorrectly = done;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public boolean isDoneCorrectly() {
        return doneCorrectly;
    }
}
