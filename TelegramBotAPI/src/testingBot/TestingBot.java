package testingBot;

import telegramBotApi.*;

public class TestingBot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TBot bot = new TBot("137720988:AAHvm9h_RSjiM7klwLZLeuBh-w2scQqJoCI");
		while(!bot.throwMessages()){}
		TMessage msg;
		String[][] key = {{"hola", "buenos"},{"dias"}};
		TKeyboard keyb = new TKeyboard(key, false, true, false);
		while(true){
			while((msg = bot.nextMsg(10)) == null){}
			while(!bot.sendText("Holaholas", msg.getChat(), keyb)){}
		}
	}

}
