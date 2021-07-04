

public class ExNegativeNumber  extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExNegativeNumber(int negativeNumber) {
        super("Estimated manpower should not be zero or negative.\nConsider changing " + negativeNumber + " to a positive non-zero amount.");
    }
    public ExNegativeNumber (String message) { super(message); }
}
