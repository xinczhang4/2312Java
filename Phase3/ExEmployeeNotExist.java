

public class ExEmployeeNotExist extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExEmployeeNotExist() {
        super("Employee name does not exist.");
    }
    public ExEmployeeNotExist (String message) { super(message); }
}
