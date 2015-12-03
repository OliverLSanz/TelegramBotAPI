package telegramBotApi;

import javax.json.JsonObject;

public class TMessage {
	
	private TChat chat;
	private boolean hasText;
	private String text;
	
	public TMessage(JsonObject jsonMessage){
		chat = new TChat(jsonMessage.getJsonObject("chat"));
		try{
			text = jsonMessage.getString("text");
			hasText = true;
		}catch(Exception e){
			hasText = false;
		}
	}
	
	public TChat getChat(){
		return chat;
	}
	
	public boolean hasText(){
		return hasText;
	}
	
	public String getText(){
		return text;
	}
	
}