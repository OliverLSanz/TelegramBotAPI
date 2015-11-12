package testingBot;

import telegramBotApi.*;

public class TestingBot {
	public static void main(String[] args){
		TelegramBot bot = new TelegramBot("137720988:AAHvm9h_RSjiM7klwLZLeuBh-w2scQqJoCI");
		while(!bot.throwMessages()){}
		TelegramMsg msg;
		
		while(true){
			while((msg = bot.nextMsg(10)) == null){}
			while(!bot.sendText("Holahola", msg.getChat().getId())){}
		}
	}
}
