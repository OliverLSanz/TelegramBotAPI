package telegramBotApi;

import javax.json.JsonObject;

public class TChat {
	
	private int id;
	
	public TChat(JsonObject jsonChat){
		id = jsonChat.getInt("id");
	}
	
	public TChat(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
}
