package controllers.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import play.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import models.Community;
import models.User;
import controllers.utils.Constant;
import controllers.utils.InfosUrl;
import controllers.utils.ReturnCode;

/**
 * RequestMaker : do all httpPost 
 * 				  Call JsonParser to create or decode Json object 
 * @author Stephane
 *
 */
public class RequestMaker {

	private String _Url;
	private int _idReq;
	
	private String json;
	/**
	 * RequestMaker Ctor
	 * @param pUrl: Url to retrieve
	 * @param pContext : context of the view
	 */
	public RequestMaker(int pIdReq){
		_idReq = pIdReq;
		_Url = InfosUrl.UrlMap.get(_idReq);
	}

	/**
	 * doRequest : make the request to the server
	 * @param pObject : object to send to serveur
	 * @return : return code of the http response 
	 */
	public int doRequest(Object pObject){
		try	{
			//Create my params and add my JSON object
			Map<String, String> kvPairs = new HashMap<String, String>();
			String JsonObject = getParamsToSend(pObject);
			kvPairs.put("params", JsonObject);
			Logger.info("JsonParser created "+ JsonObject);
			//create my httpCLient - HttpPost
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(_Url);

			httppost.setEntity(createEntityForRequest(kvPairs));
			//httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			//execute and get the response
			HttpResponse response;
			response = httpclient.execute(httppost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));        

			//parsing response
			StringBuilder sb = new StringBuilder();		    
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String pMessage = sb.toString();
			Logger.info("Message received: "+pMessage);
			String lCode = JsonParser.parseJsonGetReturnCode(pMessage);
			if(lCode != null){
				json = pMessage;
				return Integer.parseInt(lCode);
			}
		}
		catch (Exception e) {
			Logger.error("RequestMaker: request error:",e);
		}
		return ReturnCode.CODE_UNKNOWN_ERROR;
	}
	
	
	public String getJson(){
		return json;
	}

	/**
	 * getParamsToSend : parse object to JsonObject 
	 * @param pObject 
	 * @return String : json object  
	 */
	private String getParamsToSend(Object pObject){
		try	{
			switch(_idReq)	{
			case Constant.REQ_DESINSCRIPTION:
				return JsonParser.parseJsonForDesinscription();
			case Constant.REQ_GROUP_CREATE: 
				return JsonParser.parseJsonForCreateGroup((String)pObject);
			case Constant.REQ_GROUP_QUIT: //delete and quit has to send the same thing to the server
			case Constant.REQ_GROUP_DELETE:
				return JsonParser.parseJsonForQuitGroup((Community)pObject);
			case Constant.REQ_GROUP_RESP_INVITE:
				break;
			case Constant.REQ_GROUP_UPDATE:
				return JsonParser.parseJsonForUpdateGroup((String)pObject);
			case Constant.REQ_GROUPS:
				return JsonParser.parseJsonForGetGroups();
			case Constant.REQ_LOG: 
				return JsonParser.parseJsonForLogin((User)pObject);
			default : 
				break;
			}
		}
		catch (Exception e) {
		}
		return "";
	}
	
	
	/**
	 * createEntityForRequest: create Entity from a Map 
	 * @param pkvPairs : Map with Json Object
	 * @return HttpEntity 
	 */
	static private HttpEntity createEntityForRequest(Map<String, String> pkvPairs)	{
		try	{
			if (pkvPairs != null && pkvPairs.isEmpty() == false) {
				java.util.List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(pkvPairs.size());
				String k, v;
				Iterator<String> itKeys = pkvPairs.keySet().iterator();
				while (itKeys.hasNext()) {
					k = itKeys.next();
					v = pkvPairs.get(k);
					nameValuePairs.add(new BasicNameValuePair(k, v));
				}
				return (new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			}
		}
		catch (Exception e) {
			Logger.error("RequestMaker createEntityForRequest exception "+e.getMessage());
		}
		return null;
	} 

	private boolean dispacthResponsForParser(String pMessage){
		try	{
			switch(_idReq){
			case Constant.REQ_DESINSCRIPTION://Do nothing
				break;
			case Constant.REQ_GROUP_CREATE: 
				return JsonParser.parseJsonFromCreateGroupAndSave(pMessage);
			case Constant.REQ_GROUP_DELETE:
			case Constant.REQ_GROUP_QUIT://do the same fo delete and quit group
				return JsonParser.parseJsonFromQuitGroupAndSave(pMessage);
			case Constant.REQ_GROUP_RESP_INVITE://not here, do via email
				break;
			case Constant.REQ_GROUP_UPDATE:
				return JsonParser.parseJsonFromUpdateGroupAndSave(pMessage);
			case Constant.REQ_GROUPS:
				return true;//JsonParser.parseJsonFromGetGroups(pMessage);
			case Constant.REQ_INSCRIPTION: 
				return JsonParser.parseJsonFromInscriptionAndSave(pMessage);
			case Constant.REQ_LOG: 
				return JsonParser.parseJsonFromLoginAndSave(pMessage);
				
			default : 
				break;
			}
		}
		catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * dispacthResponseIfNothingOnSrv : put the sessionsObject to null if notging found on servers
	 * @param pMessage
	 * @return true, if done
	 * 			false otherwise
	 */
	private boolean dispacthResponseIfNothingOnSrv(String pMessage)	{
		try	{
			switch(_idReq) {
			case Constant.REQ_BILLS:
				break;
			case Constant.REQ_GROUP_USERS:
				break;
			case Constant.REQ_GROUPS:
				break;
			case Constant.REQ_LIST_ITEMS:
				break;
			case Constant.REQ_LISTS:
				break;
			case Constant.REQ_NOTES:
				break;
			default : 
				break;
			}
			return true;
		}
		catch (Exception e) {
		}
		return false;
	}
}
