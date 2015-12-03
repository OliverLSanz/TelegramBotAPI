package telegramBotApi;

/**
 * Stands for Telegram Sendable Message.
 * 
 * @author olisanz
 */
public class TelegramSMsg {
	
	private TelegramChat destination;
	private String text;
	private TelegramKeyboard keyboard;
	
	public TelegramSMsg(TelegramChat destination, String text){
		this.destination = destination;
		this.text = text;
	}
	
	public TelegramSMsg(TelegramChat destination, String text, TelegramKeyboard keyboard){
		this(destination, text);
		this.keyboard = keyboard;
	}
	
	public TelegramChat getDestination(){
		return destination;
	}
	
	public String getText(){
		return text;
	}
	
	public TelegramKeyboard getKeyboard(){
		return keyboard;
	}
}
