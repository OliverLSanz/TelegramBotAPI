package telegramBotApi;

import javax.json.JsonObject;

public class TelegramMsg {
	
	private TelegramChat chat;
	private boolean hasText;
	private String text;
	
	public TelegramMsg(JsonObject jsonMessage){
		chat = new TelegramChat(jsonMessage.getJsonObject("chat"));
		try{
			text = jsonMessage.getString("text");
			hasText = true;
		}catch(Exception e){
			hasText = false;
		}
	}
	
	public TelegramChat getChat(){
		return chat;
	}
	
	public boolean hasText(){
		return hasText;
	}
	
	public String getText(){
		return text;
	}
	
}