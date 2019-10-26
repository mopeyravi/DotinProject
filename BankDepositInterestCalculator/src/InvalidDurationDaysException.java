
public class InvalidDurationDaysException extends Exception{
    public InvalidDurationDaysException() {
        super("The Balance Is Bellow Zero!");
    }

    public String toString() {
        return super.toString();
    }
}
