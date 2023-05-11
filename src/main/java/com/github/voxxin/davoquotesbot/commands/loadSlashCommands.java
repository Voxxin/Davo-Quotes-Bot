package com.github.voxxin.davoquotesbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class loadSlashCommands extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        List<CommandData> davoCommand = new ArrayList<>();

        //Davo Command
        davoCommand.add(Commands.slash("davo", "Gives you a random davo quote!"));
        davoCommand.add(Commands.slash("catherine", "Gives you a random catherine quote!"));
        davoCommand.add(Commands.slash("size", "size of davo qyuotes bot!"));

        //Find Command
        OptionData stringOption = new OptionData(OptionType.STRING, "sequence", "Character sequence", true);
        OptionData requestAll = new OptionData(OptionType.BOOLEAN, "output", "Output each quote with the sequence?", true);
        davoCommand.add(Commands.slash("find", "Finds a specific character sequence!")
                .addOptions(stringOption, requestAll));

        //Add command
        OptionData inputOption = new OptionData(OptionType.STRING, "input", "Quote", true);
        davoCommand.add(Commands.slash("add", "Allows the bot owner to add quotes!")
                .addOptions(inputOption));

        event.getJDA().getGuilds().forEach(guild -> {

            guild.updateCommands().addCommands(davoCommand).queue();
        });
    }
}
