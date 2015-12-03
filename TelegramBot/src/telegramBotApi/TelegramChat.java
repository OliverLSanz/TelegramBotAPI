package telegramBotApi;

import javax.json.JsonObject;

public class TelegramChat {
	
	private int id;
	
	public TelegramChat(JsonObject jsonChat){
		id = jsonChat.getInt("id");
	}
	
	public TelegramChat(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
}
