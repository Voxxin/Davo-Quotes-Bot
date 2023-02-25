package com.github.voxxin.davoquotesbot.commands;

import com.github.voxxin.davoquotesbot.manager.databaseManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class davoCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (!command.equals("davo")) return;

        event.reply(String.valueOf(databaseManager.quotesArray.get((int) Math.floor(Math.random()*databaseManager.quotesArray.size())))).queue();
    }
}
