public class Player {
    private static int score;
    private static double time;
    private static boolean energy;
    private static int lives = 5;
    Player() {
        score = 0;
        time = 0;
        energy = false;
    }
    Player(Player p) {
        score = p.getScore();
        time = p.getTime();
        energy = p.getEnergy();
    }
    public void addTime(double time) {
        energy = true;
        Player.time+= time;
    }
    public int getScore() {
        return score;
    }
    public double getTime() {
        return time;
    }
    public void subtractTime(double time) {
        if(Player.time > 0) 
            Player.time -= time;
            if (Player.time == 0) {
                energy = false;
            }
    }
    public boolean getEnergy() {
        return energy;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        Player.lives = lives;
    }
}
