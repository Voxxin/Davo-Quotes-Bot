package com.github.voxxin.davoquotesbot.commands;

import com.github.voxxin.davoquotesbot.manager.databaseManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class findCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        String sequenceRequested = Objects.requireNonNull(event.getOption("sequence")).getAsString();
        boolean sequenceOutput = Objects.requireNonNull(event.getOption("output")).getAsBoolean();
        if (!command.equals("find")) return;
        AtomicInteger howManyTimes = new AtomicInteger();

        ArrayList<String> stringHit = new ArrayList<>();

        databaseManager.quotesArray.forEach(quote -> {
            String lowerQuote = quote.toLowerCase(Locale.ROOT);
            String lowerSequence = sequenceRequested.toLowerCase();
            if (lowerQuote.contains(lowerSequence)) {
                howManyTimes.getAndIncrement();
                quote.replace("\\'", "'");
                stringHit.add("```" + quote + "```" + "\n");
            }
        });

        event.deferReply().queue();
        if (!sequenceOutput || howManyTimes.get() == 0) {
            event.getHook().sendMessage("`" + sequenceRequested + "`" + "\n" + "Can be found: " + howManyTimes + " times.").queue();
        } else {
            String formattedStringHit = String.join("", stringHit);

            if (formattedStringHit.length() < 1930) {
                event.getHook().sendMessage(
                        "`" + sequenceRequested + "`" + "\n"
                                + "Can be found: " + howManyTimes + " times." + "\n"
                                + formattedStringHit
                ).queue();
            } else {
                event.getHook().sendMessage(
                        "`" + sequenceRequested + "`" + "\n"
                                + "Can be found: " + howManyTimes + " times." + "\n"
                                + "\n"

                        + "Total quotes is too large. Keep doing /davo to find each of them :wink:"
                ).queue();
            }
        }
    }
}
