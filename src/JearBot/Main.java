package JearBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class Main {
    public static CustomsMaker customsMaker;
    public static String prefix = "~jear";
    public static JDA jda;

    //EFFECTS: main method
    public static void main(String[] args) throws LoginException {
        customsMaker = new CustomsMaker(new ArrayList<>());
        JDABuilder builder = JDABuilder.createDefault("");
        jda = builder.setToken("ODQxODA3Mzc0NDczMzYzNDY3.YJsINg.1vDb3U_cOeuAW1232vbEMCu7P9E").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("with your heart owo"));

        jda.addEventListener(new Commands());

    }
}
