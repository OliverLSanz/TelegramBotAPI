package telegramBotApi;

/**
 * Stands for Telegram Sendable Message.
 * 
 * @author olisanz
 */
public class TSendableMessage {
	
	private TChat destination;
	private String text;
	private TKeyboard keyboard;
	
	public TSendableMessage(TChat destination, String text){
		this.destination = destination;
		this.text = text;
	}
	
	public TSendableMessage(TChat destination, String text, TKeyboard keyboard){
		this(destination, text);
		this.keyboard = keyboard;
	}
	
	public TChat getDestination(){
		return destination;
	}
	
	public String getText(){
		return text;
	}
	
	public TKeyboard getKeyboard(){
		return keyboard;
	}
}
