package JearBot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomsMaker {
    List<String> players;

    public CustomsMaker(List<String> players) {
        this.players = players;
    }

    public List<String> getPlayers() {
        return this.players;
    }

    public String getCurrentPlayers() {
        String currentPlayers = "Current Players: ";
        for (String player : players) {
            currentPlayers += "\n" + player;
        }
        return currentPlayers;
    }

    public void clearPlayers(){
        this.players = new ArrayList<>();
    }

    public void shufflePlayers() {
        Collections.shuffle(this.players);
    }

    public String getTeams(){
        int team1Size = players.size() / 2;
        String team1 = "";
        String team2 = "";
        for(int i = 0; i < team1Size; i++) {
            team1 += players.get(i) + " ";
        }
        for (int i = team1Size; i < players.size(); i++) {
            team2 += players.get(i) + " ";
        }
        return ("Team 1: " + team1 + "\n Team 2: " + team2);
    }

    public void deletePlayers(List<String> playersToDelete) {
        for (String player : playersToDelete) {
            if (this.players.contains(player)) {
                this.players.remove(player);
            }
        }
    }
}
