package testingBot;

import telegramBotApi.*;

public class TestingBot {
	public static void main(String[] args){
		TelegramBot bot = new TelegramBot("137720988:AAHvm9h_RSjiM7klwLZLeuBh-w2scQqJoCI");
		do bot.throwMessages(); while(bot.failed());
		TelegramMsg msg;
		
		while(true){
			do msg = bot.nextMsg(10); while (bot.failed());
			do bot.sendText("Holahola", msg.getChat().getId()); while(bot.failed());
		}
	}
}
