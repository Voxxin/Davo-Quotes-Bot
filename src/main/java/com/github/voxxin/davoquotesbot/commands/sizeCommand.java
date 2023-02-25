package com.github.voxxin.davoquotesbot.commands;

import com.github.voxxin.davoquotesbot.manager.databaseManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class sizeCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (!command.equals("size")) return;

        event.reply("Size of these quotes is: " +
                databaseManager.quotesArray.size()).queue();
    }
}
