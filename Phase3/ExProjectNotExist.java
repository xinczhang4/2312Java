

public class ExProjectNotExist extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExProjectNotExist() {
        super("Project does not exist.");
    }
    public ExProjectNotExist (String message) { super(message); }
}

