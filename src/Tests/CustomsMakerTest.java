package Tests;

import JearBot.CustomsMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomsMakerTest {
    List<String> testPlayers;
    CustomsMaker testCustomsMaker;

    @BeforeEach
    void setup() {
        testPlayers = new ArrayList<>();
        testPlayers.add("Bob1");
        testPlayers.add("Bob2");
        testPlayers.add("Bob3");
        testPlayers.add("Bob4");
        testPlayers.add("Bob5");
        testPlayers.add("Bob6");
    }

    @Test
    public void getTeamsTestEven() {
        testCustomsMaker = new CustomsMaker(testPlayers);
        assertEquals("Team 1: Bob1 Bob2 Bob3 \n" +
                "Team 2: Bob4 Bob5 Bob6 ", testCustomsMaker.getTeams());
    }

    @Test
    public void getTeamsTestOdd() {
        testPlayers.add("Bob7");
        testCustomsMaker = new CustomsMaker(testPlayers);
        assertEquals("Team 1: Bob1 Bob2 Bob3 \n" +
                "Team 2: Bob4 Bob5 Bob6 Bob7 ", testCustomsMaker.getTeams());
    }

    @Test
    public void testDeletePlayers() {
        testCustomsMaker = new CustomsMaker(testPlayers);
        List<String> testPlayersToDelete = new ArrayList<>();
        testPlayersToDelete.add("Bob1");
        testPlayersToDelete.add("Bob2");
        testPlayersToDelete.add("Bob3");

        testCustomsMaker.deletePlayers(testPlayersToDelete);
        assertTrue(testCustomsMaker.getPlayers().size() == 3);
        assertFalse(testCustomsMaker.getPlayers().contains("Bob1"));
        assertFalse(testCustomsMaker.getPlayers().contains("Bob2"));
        assertFalse(testCustomsMaker.getPlayers().contains("Bob3"));
        assertTrue(testCustomsMaker.getPlayers().contains("Bob4"));
        assertTrue(testCustomsMaker.getPlayers().contains("Bob5"));
        assertTrue(testCustomsMaker.getPlayers().contains("Bob6"));

    }


}
