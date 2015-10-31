
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.json.*;
 
/**
  * @author Óliver Sanz San José
  * @version 1.0.0
  */
public class TelegramBot {

	private ArrayList<TelegramMsg> msgs;
	private Iterator<TelegramMsg> msgQueue;
	private int msgOffSet;
	private final String botUrl;
	
	/**
	 * Initializes the bot with its id.
	 * 
	 * @param	botId	the id of the bot, as given by BotFather.
	 */
	public TelegramBot(String botId){
		// Creates the URL that will be used for the requests to the Telegram bot API
	  	botUrl = "https://api.telegram.org/bot" + botId + "/";
	  	// Initialises the ArrayList and Iterator for the fetched messages.
		msgs = new ArrayList<TelegramMsg>();
		msgQueue = msgs.iterator();
	}
	
	/**
	 * Sends a text to a chat.
	 * 
	 * @param	text to send encoded in utf-8
	 * @param	chatId is the id of the chat where the text will be sent
	 * @throws	UnsupportedEncodingException
	 */
	public void sendText(String text, int chatId) throws UnsupportedEncodingException{
		String urlString = botUrl +"sendmessage?chat_id="+
			 chatId+"&text="+URLEncoder.encode(text, "UTF-8");
		
		try {
			new URL(urlString).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * It gets the first unseen message sent to the bot as a TelegramMsg, if there is none,
	 * it waits some time for one to arrive.
	 * @param waitTime is the seconds to wait for a new message if there is none
	 * @return the first unreturned message
	 */
	public TelegramMsg nextMsg(int waitTime) throws Exception{
		if(!msgQueue.hasNext()){
			msgs.clear();
			getMessages(waitTime);
		}
		return msgQueue.next();
	}

	/**
	 * Fills msgQueue;
	 */
	public void getMessages(int waitTime){
		URL url;
		try {
			url = new URL(botUrl + "getupdates?timeout="+waitTime+"&offset=" + msgOffSet);
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray resultsx = obj.getJsonArray("result");
			for (JsonObject result : resultsx.getValuesAs(JsonObject.class)) {
				msgs.add(new TelegramMsg(result.getJsonObject("message")));
				msgOffSet = result.getInt("update_id") + 1;
			}
			msgQueue = msgs.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void throwMessages(){
		getMessages(0);
		msgs.clear();
	}
}
