# Davo-Quotes-Bot
This is a simple discord quotes bot I made using [JDA](https://github.com/DV8FromTheWorld/JDA) and using [MongoDB](https://www.mongodb.com/).

# Commands 
/davo - Gets you a random davo quote

/size - Gets you the total size

/find sequence[] output[]- Gets you the amount of times the requested sequence is. And if chosen, will retrun each sequence with output set to true.

/add input[] - This command will only work for the but owner, when the owner's ID is proper put inside the class. It will add the quote requested. Also `:lb` can be used to do a linebreak.

# How make it your own

## .env
There will be a file called .env, this will hold the tokens for your bot to work.
Make sure to just set the following varibales to your own.

### TOKEN= 
Put your discord bot's token ID here.

### DB_CONNECTION=
Put your MongoDB cluster username and password here.

### Database setup
You can put your database name [here](https://github.com/Voxxin/Davo-Quotes-Bot/blob/master/src/main/java/com/github/voxxin/davoquotesbot/davoQuotesBot.java)

You can put your collection name [here](https://github.com/Voxxin/Davo-Quotes-Bot/blob/master/src/main/java/com/github/voxxin/davoquotesbot/manager/databaseManager.java)

### Your ID
Make sure to change my ID for your discord user ID [here](https://github.com/Voxxin/Davo-Quotes-Bot/blob/master/src/main/java/com/github/voxxin/davoquotesbot/commands/addCommand.java)
