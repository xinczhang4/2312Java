

public class ExTeamNotAvailable extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExTeamNotAvailable(String tname, String date) {
        super("The team is not available during the period " +  date);
    }
    public ExTeamNotAvailable (String message) { super(message); }
}


