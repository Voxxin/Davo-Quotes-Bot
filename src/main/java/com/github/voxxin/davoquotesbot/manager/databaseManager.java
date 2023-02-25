package com.github.voxxin.davoquotesbot.manager;

import com.github.voxxin.davoquotesbot.davoQuotesBot;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class databaseManager {
    public static Map<String, Document> quotes = new HashMap<>();
    public static ArrayList<String> quotesArray = new ArrayList<>();
    static MongoCollection<Document> davoQuotesTotal = davoQuotesBot.database.getCollection("davo-quotes");

    public databaseManager() {
        quotesArray.clear();

        davoQuotesTotal.find().forEach(quote -> {
            String formattedQuote = quote.getString("quote");
            quotes().put("quote", quote);

            quotesArray.add(formattedQuote);
        });
    }

    public static Map<String, Document> quotes() { return quotes;}

    public static void createQuote(String Quote) {
        //System.out.println(Quote);
        if (quotesArray.toString().contains(Quote)) return;

        Document newTicket = new Document()
                .append("quote", Quote);
        davoQuotesTotal.insertOne(newTicket);

        refreshQuotes();
    }

    private static void refreshQuotes() {
        quotesArray.clear();

        davoQuotesTotal.find().forEach(perQuote -> {
            String formattedQuote = perQuote.getString("quote");
            quotes().put("quote", perQuote);

            //System.out.println(formattedQuote);
            quotesArray.add(formattedQuote);
        });
    }
}
