package telegramBotApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.json.*;
 
/**
  * @author	Óliver Sanz San José
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
	 * @return	true if everything went as expected, false, if an error
	 * 			occurred.
	 * @throws	UnsupportedEncodingException
	 */
	public boolean sendText(String text, int chatId){
		try {
			String urlString = botUrl +"sendmessage?chat_id="+
			 chatId+"&text=" + URLEncoder.encode(text, "UTF-8");
			
			InputStream is = new URL(urlString).openStream();
			JsonReader rdr = Json.createReader(is);
			if(!rdr.readObject().getBoolean("ok")){
				throw new TelegramBotAPIException();
			}
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return false;
		} catch (IOException e){
			e.printStackTrace();
			return false;
		} catch (TelegramBotAPIException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Sends the specified request to the bot, and returns a JsonValue 
	 * that contains the response of the Telegram Bot API. The request
	 * is specified trough a String that contains the method and arguments
	 * part of a normal https request to the API.
	 * 
	 * Example of argument's string:
	 * 		"getUpdates"
	 * 
	 * @param	request
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws TelegramBotAPIException 
	 */
	private JsonValue request(String request){
		try{
			InputStream is = new URL(botUrl + request).openStream();
			JsonReader rdr = Json.createReader(is);
			if(!rdr.readObject().getBoolean("ok")){
				throw new TelegramBotAPIException();
			}
			return rdr.readObject().get("result");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * It gets the first unseen message sent to the bot as a TelegramMsg, if there is none,
	 * it waits some time for one to arrive.
	 * 
	 * @param waitTime is the seconds to wait for a new message if there is none
	 * @return the first unreturned message
	 */
	public TelegramMsg nextMsg(int waitTime){
		boolean failed;
		
		try{
			if(!msgQueue.hasNext()){
				msgs.clear();
				failed = !getMessages(waitTime);
				if(failed) throw new Exception();
			}
			return msgQueue.next();
		} catch(Exception e){
			return null;
		}
	}

	/**
	 * Fills msgQueue;
	 */
	public boolean getMessages(int waitTime){
		try {
			URL url = new URL(botUrl + "getupdates?timeout=" + waitTime + "&offset=" + msgOffSet);
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray resultsx = obj.getJsonArray("result");
			for (JsonObject result : resultsx.getValuesAs(JsonObject.class)) {
				msgs.add(new TelegramMsg(result.getJsonObject("message")));
				msgOffSet = result.getInt("update_id") + 1;
			}
			msgQueue = msgs.iterator();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean throwMessages(){
		try{
			if(!getMessages(0)) throw new Exception();
			msgs.clear();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
