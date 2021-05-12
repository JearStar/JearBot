package JearBot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

import static JearBot.Main.customsMaker;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix)) {
            switch (args[1].toLowerCase()) {
                case "hello":
                    event.getChannel().sendMessage("Hey there, I'm alive.").queue();
                    break;
                case "hi":
                    event.getChannel().sendMessage("Howdy").queue();
                    break;
                case "customsadd":
                    List<String> players = Arrays.asList(args).subList(2, args.length);
                    for (String player : players) {
                        customsMaker.players.add(player);
                    }
                    event.getChannel().sendMessage(customsMaker.getCurrentPlayers()).queue();
                    break;
                case "customsgetplayers":
                    event.getChannel().sendMessage(customsMaker.getCurrentPlayers()).queue();
                    break;
                case "customsshuffle":
                    customsMaker.shufflePlayers();
                    event.getChannel().sendMessage("Shuffled successfully.").queue();
                    break;
                case "customsgetteams":
                    event.getChannel().sendMessage(customsMaker.getTeams()).queue();
                    break;
                case "customsclear":
                    customsMaker.clearPlayers();
                    event.getChannel().sendMessage(customsMaker.getCurrentPlayers()).queue();
                    break;
                case "customsdelete":
                    List<String> playersToDelete = Arrays.asList(args).subList(2, args.length);
                    customsMaker.deletePlayers(playersToDelete);
                    event.getChannel().sendMessage(customsMaker.getCurrentPlayers()).queue();
                    break;
                case "help":
                    event.getChannel().sendMessage("Here is the full documentation for me: \n " +
                            "https://docs.google.com/document/d/11TOlKvMxdEcCdbnhLqljsfHb2LHHyP2lO3F7269xfkM/edit?usp=sharing").queue();
                    break;
            }


        }

    }
}

