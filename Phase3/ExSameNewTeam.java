

public class ExSameNewTeam extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExSameNewTeam() {
        super("The old and new teams should not be the same.");
    }
    public ExSameNewTeam (String message) { super(message); }
}
