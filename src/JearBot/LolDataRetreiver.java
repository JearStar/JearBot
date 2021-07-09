package JearBot;


import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class LolDataRetreiver {
    private static String API = "RGAPI-d1550267-c887-4dbb-87f4-8fb711855699";
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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public LolDataRetreiver(String name) {
        this.name = name;
        try {
            this.playerInfo = retrieveJsonFile("https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name +
                    "?api_key" +
                    "=" + API);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.puuid = playerInfo.getString("puuid");
        this.summonerLevel = playerInfo.getLong("summonerLevel");
        this.summonerID = playerInfo.getString("id");
//        try {
//            this.rankInfo = retrieveJsonFile("https://na1.api.riotgames.com/lol/league/v4/entries/by-summoner/"
//                    + summonerID + "?api_key=" + API);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.tier = rankInfo.getString("tier");
//        this.rank = rankInfo.getString("rank");
//        this.leaguePoints = rankInfo.getInt("leaguePoints");
//        this.wins = rankInfo.getInt("wins");
//        this.losses = rankInfo.getInt("losses");
    }

    public String summarizeData() {
        return "Name: " + this.name + "\nLevel: " + this.summonerLevel;
//                + "\nRank: " + this.tier + " " + this.rank +
//                "\nWins: " + this.wins + "\nLosses: " + this.losses;
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

    public JSONObject retrieveJsonFileRank(String url) throws IOException {
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
}
