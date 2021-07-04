

public class ExTeamNotExist extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExTeamNotExist() {
        super("Team does not exist.");
    }
    public ExTeamNotExist (String message) { super(message); }
}
