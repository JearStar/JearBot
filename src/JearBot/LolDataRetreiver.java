package JearBot;


import Exceptions.RankInfoException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class LolDataRetreiver {
    private static String API = "RGAPI-0d9e554f-c51d-42ae-862c-45720d5cfc5b";
    private String name;
    private String puuid;
    private Long summonerLevel;
    private String summonerID;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    JSONObject playerInfo;
    JSONObject rankInfo;


    // CONSTRUCTOR
    public LolDataRetreiver(String name) {
        this.name = name;
        try {
            this.playerInfo = retrieveJsonFile("https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name +
                    "?api_key" +
                    "=" + API);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = playerInfo.getString("name");
        this.puuid = playerInfo.getString("puuid");
        this.summonerLevel = playerInfo.getLong("summonerLevel");
        this.summonerID = playerInfo.getString("id");
        try {
            this.rankInfo = retrieveJsonFileRank("https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/"
                    + summonerID + "?api_key=" + API);
            this.tier = rankInfo.getString("tier");
            this.rank = rankInfo.getString("rank");
            this.leaguePoints = rankInfo.getInt("leaguePoints");
            this.wins = rankInfo.getInt("wins");
            this.losses = rankInfo.getInt("losses");
        } catch (IOException e) {
            e.printStackTrace();

        } catch (RankInfoException e) {
            this.tier = null;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getPuuid() {
        return this.puuid;
    }

    public String getSummonerID() {
        return this.summonerID;
    }

    public long getSummonerLevel() {
        return this.summonerLevel;
    }

    public String getTier() {
        return this.tier;
    }

    public String getRank() {
        return this.rank;
    }

    public int getLeaguePoints() {
        return this.leaguePoints;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    //EFFECTS: summarizes a user's profile and returns it as a string
    public String summarizeData() {
        if (this.tier == null) {
            return "Name: " + this.name + "\nLevel: " + this.summonerLevel
                    + "\nRank: Unranked";
        }
        return "Name: " + this.name + "\nLevel: " + this.summonerLevel
                + "\nRank: " + this.tier + " " + this.rank + " " + this.leaguePoints + "LP" +
                "\nWins: " + this.wins + "\nLosses: " + this.losses;
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject retrieveJsonFile(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public JSONObject retrieveJsonFileRank(String url) throws IOException, RankInfoException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            if (jsonText.substring(0, 1) == "{") {
                throw new RankInfoException();
            }
            jsonText = jsonText.substring(1, jsonText.length() - 1);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
