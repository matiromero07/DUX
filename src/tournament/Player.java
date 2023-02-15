package tournament;

public class Player {
    private String name;
    private double winProbability;

    public Player(String name, double winProbability) {
        this.name = name;
        this.winProbability = winProbability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWinProbability() {
        return winProbability;
    }

    public void setWinProbability(double winProbability) {
        this.winProbability = winProbability;
    }
}
