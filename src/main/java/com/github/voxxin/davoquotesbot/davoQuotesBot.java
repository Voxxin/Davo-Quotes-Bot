package com.github.voxxin.davoquotesbot;

import com.github.voxxin.davoquotesbot.commands.*;
import com.github.voxxin.davoquotesbot.manager.databaseManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class davoQuotesBot {
    private static Dotenv config;

    private final ShardManager shardManager;

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static MongoClient mongoClient = null;
    public static MongoDatabase database = null;

    public davoQuotesBot() throws LoginException {
        config = Dotenv.configure().load();

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("/davo"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        shardManager = builder.build();

        //Register Listeners
        shardManager.addEventListener(
                new davoCommands(),
                new sizeCommand(),
                new findCommand(),
                new addCommand(),
                new loadSlashCommands()
        );

        //Database Stuff :3
        mongoClient = MongoClients.create(Dotenv.configure().load().get("DB_CONNECTION"));
        loadDb();
    }

    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) {
        try {
            davoQuotesBot bot = new davoQuotesBot();
        } catch (LoginException e) {
            System.out.println("ERROR: Bot token invalid.");
        }
    }

    private void loadDb() {
        if (mongoClient == null) {
            System.out.println("Could not load database, it's missing it's uri!");
            return;
        }
        database = mongoClient.getDatabase("autre");
        new databaseManager();
    }
}
