package tennis;

public class Game {
    private final int[] GAME_POINTS = {0, 15, 30, 40};
    private final String[] GAME_STATUS = {"Ongoing", "Finished"};

    private Player player1;
    private Player player2;
    private String currentStatus;
    private boolean deuce = false;

    public Game(Player p1, Player p2){
        player1 = p1;
        player2 = p2;
        setCurrentStatus(GAME_STATUS[0]);
    }

    /**
     *
     * @param p the player who scored
     */
    public void scorePoint(Player p){
        System.out.println(p.getName() + " scored !");

        int score = p.getScore();
        Player otherPlayer = p == player1 ? player2 : player1;
        if(deuce){
            p.setAdvantage(true);
            setDeuce(false);
            return;
        }
        if(p.isAdvantage()){
            p.setWinner(true);
            endGame();
            return;
        }
        if(score == 3){
            if(otherPlayer.isAdvantage()){
                otherPlayer.setAdvantage(false);
                setDeuce(true);
            } else {
                p.setWinner(true);
                endGame();
            }
            return;
        }

        if(score == 2){
            p.setScore(++score);
            if(otherPlayer.getScore() == 3){
                setDeuce(true);
            }
        }
        else{
            p.setScore(++score);
        }
    }

    private void endGame() {
        setCurrentStatus(GAME_STATUS[1]);
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    private void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public boolean isDeuce() {
        return deuce;
    }

    public void setDeuce(boolean deuce) {
        this.deuce = deuce;
    }

    @Override
    public String toString() {
        return "tennis.Game scores (" + currentStatus + ") : \n" +
                " tennis.Player 1 (" + player1.getName() + ") : " + GAME_POINTS[player1.getScore()] + (player1.isWinner() ? " <winner>" : "") + " \n" +
                " tennis.Player 2 (" + player2.getName() + ") : " + GAME_POINTS[player2.getScore()] + (player2.isWinner() ? " <winner>" : "") + "\n";
    }
}
