public class LeaderMembership extends Membership{

    public LeaderMembership(Team t, Day d) {
        super(t, d);
    }

    @Override
    public String getDetail(){
        return super.getJoinedTeam() + " (" + super.getJoinDay() + " to --), as a leader";
    }
}
