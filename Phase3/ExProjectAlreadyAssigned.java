

public class ExProjectAlreadyAssigned extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExProjectAlreadyAssigned() {
        super("Project has already been assigned to a team.");
    }
    public ExProjectAlreadyAssigned (String message) { super(message); }
}

