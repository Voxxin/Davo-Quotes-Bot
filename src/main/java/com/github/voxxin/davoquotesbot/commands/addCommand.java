package com.github.voxxin.davoquotesbot.commands;

import com.github.voxxin.davoquotesbot.manager.databaseManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class addCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (!command.equals("add")) return;
        event.deferReply().queue();

        //MAKE SURE TO PUT YOUR DISCORD ID HERE
        if (!event.getUser().getId().equals("852298403210985472")) {
            event.getHook().sendMessage("hm... you're not the owner of this bot, loserr").queue();
            return;
        }

        String quoteToBeAdded = Objects.requireNonNull(event.getOption("input")).getAsString();

        quoteToBeAdded = quoteToBeAdded.replace(":lb", "\n");

        databaseManager.createQuote(quoteToBeAdded);

        event.getHook().sendMessage("Added as quote number " + databaseManager.quotesArray.size()).queue();
    }
}
