package Tests;

import JearBot.LolDataRetreiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LolDataRetrieverTest {
    LolDataRetreiver testRetriever;

    @BeforeEach
    void setup() {
        testRetriever = new LolDataRetreiver("jearstar07");
    }

    @Test
    public void testConstructor() {
        assertEquals("jearstar07", testRetriever.getName());
        assertEquals("3jFKkucsYXRwrZO7u3chHDCGuMY7fc81IK1MIr_M_KFJfHMO13TG59YM6bUWaaFu32jqlrAZvYmebA",
                testRetriever.getPuuid());
        assertEquals("7cdRLyGluPjeCXYB-0lP2x_jiNsQubm88VI3yaPIn6_tIog", testRetriever.getSummonerID());
    }
}
