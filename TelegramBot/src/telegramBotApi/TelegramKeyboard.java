package telegramBotApi;

import javax.json.*;

public class TelegramKeyboard {
	
	JsonObject keyboard;
	
	/**
	 * Full constructor of a TelegramKeyboard.
	 * 
	 * @param	keyboard	first dimension specify rows, second,
	 * 						the buttons at each row.
	 * @param	resizeKeyboard
	 * @param	oneTimeKeyboard
	 * @param	selective
	 */
	public TelegramKeyboard(String[][] keyboard, boolean resizeKeyboard,
			boolean oneTimeKeyboard, boolean selective){
		JsonArrayBuilder keyboardBuilder = Json.createArrayBuilder();
		JsonArrayBuilder rowBuilder;
		
		for(String[] row : keyboard){
			rowBuilder = Json.createArrayBuilder();
			for(String button : row){
				rowBuilder.add(button);
			}
			keyboardBuilder.add(rowBuilder);
		}
		
		this.keyboard = Json.createObjectBuilder()
				.add("keyboard", keyboardBuilder)
				.add("resize_keyboard", resizeKeyboard)
				.add("one_time_keyboard", oneTimeKeyboard)
				.add("selective", selective)
			.build();
	}
	
	public String toString(){
		return keyboard.toString();
	}
}
