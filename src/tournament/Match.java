package tournament;

public class Match {
    private String nameTournament;
    private int set;

    public Match(String nameTournament, int set) {
        this.nameTournament = nameTournament;
        this.set = set;
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }
}
