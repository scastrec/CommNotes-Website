package controllers.requests;


import java.util.LinkedList;

import models.Community;
import models.User;
import models.Bill;
import models.ItemList;
import models.Liste;
import models.Note;
import models.NoteInfo;
import models.NoteTodo;
import models.PaymentByUser;
import controllers.utils.Constant;
import play.Logger;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * JsonParser : Parse all the data. Object to json and Json to object
 * 				called from RequestMaker class
 * @author Stephane
 *
 */
public class JsonParser {

	/************************************************************************************************/
	/*********************************** Return CODE ************************************************/
	/************************************************************************************************/
	/**
	 * parseJsonGetReturnCode : parse the value of the return
	 * @param pMessageFromSrv : JSON message from server 
	 * @return String : code value of the return 
	 */
	static public String parseJsonGetReturnCode(String pMessageFromSrv)	{
		try	{
			JSONObject jObject = new JSONObject(pMessageFromSrv);			
			return jObject.getString("value");		
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonGetReturnCode exception : "+e.getMessage());
		}
		return null;
	}


	/************************************************************************************************/
	/**************************************** INSCRIPTION *******************************************/
	/************************************************************************************************/	
	/**
	 * parseJsonForInscription
	 * @param User : user that request the inscription
	 * @return : JsonObject in string format
	 */
	static public String parseJsonForInscription(User pUser){
		try	{
			//Create json object from user 
			JSONObject data = new JSONObject();
			JSONObject jUser = new JSONObject();

			data.put("mail", pUser.getMail());//put mail
			data.put("name", pUser.getName());//put name
			data.put("password", pUser.getPwd());//put pwd
			jUser.put("user",data);//put all in user object

			return jUser.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForInscription exception : "+e.getMessage());
		}
		return null;
	}
	/**
	 * parseJsonFromInscription : parse string from server to a User
	 * @param pMessageFromSrv
	 * @return User
	 */
	static public boolean parseJsonFromInscriptionAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);
			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("user");

			JSONObject lUserObject = lUserArray.getJSONObject(0);
			//get the user information from the JSON object
			String pId = lUserObject.getString("id"); //get Id
			String pName = lUserObject.getString("name");//get name
			String pMail = lUserObject.getString("mail");//get mail
			return true; 
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromInscriptionAndSave exception : "+e.getMessage());
		}
		return false;
	} 
	
	/**
	 * parseJsonForDesinscription : create Json object to unsubscribe of this app
	 * @return JsonObject as String
	 */
	static public String parseJsonForDesinscription(){
		try	{
			JSONObject jObject = new JSONObject();
			JSONObject jUser = new JSONObject();
			
			//TODO
			jUser.put("id", "");
			jObject.put("user", jUser);
			
			return jObject.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDesinscription exception "+e.getMessage());
		}
		return "";
	}


	/************************************************************************************************/
	/**************************************** LOGIN *************************************************/
	/************************************************************************************************/


	/**
	 * parseJsonForLogin : parse user information for login 
	 * @param pUser : user to log in
	 * @return Json object in string format
	 */
	static public String parseJsonForLogin(User pUser){
		try	{
			//Create json object from user
			JSONObject data = new JSONObject();
			JSONObject jUser = new JSONObject();

			data.put("mail", pUser.getMail()); //put mail
			data.put("password",pUser.getPwd());//put pwd
			jUser.put("user",data);
			return jUser.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForLogin exception : "+e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * parseJsonFromLogin : parse information from server for login request 
	 * @param pMessageFromSrv : Message from server which contains Json Object 
	 * @return
	 */
	static public boolean parseJsonFromLoginAndSave(String pMessageFromSrv) {
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("user");
			//get the user information from the JSON array
			String pName = lUserArray.getJSONObject(0).getString("name");
			String pMail = lUserArray.getJSONObject(0).getString("mail");
			String pId = lUserArray.getJSONObject(0).getString("id");
			User lUser = new User(pId, pMail, pName);

			return true;		
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromLoginAndSave exception : "+e.getMessage());
		}
		return false;
	} 
	
	static public java.util.List<Community> getCommunities(String pJson){
		try	{
			return parseJsonFromGetGroups(pJson);//use the parser groups to extract the groups			
		}
		catch (Exception e) {
			Logger.error("JsonParser getCommunities exception : "+e.getMessage());
		}
		return null;
	}
	

	/************************************************************************************************/
	/****************************************** GROUP ***********************************************/
	/************************************************************************************************/

	/**
	 * parseJsonForCreateGroup : create the message to create a group, 
	 * 				parsing the name of the wanted group and the author of the group
	 * @param pName: Name of the group to create
	 * @return string : message to send to the server in Json form
	 */
	static public String parseJsonForCreateGroup(String pName){
		try	{
			//Create json object from user
			JSONObject jUser = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//parse the user in Json object (just id required)
			jUser.put("id","");			
			jsonObj.put("user",jUser);
			//parse the community in Json object
			jCommunity.put("name", pName);
			jsonObj.put("community",jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCreateGroup exception : "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromCreateGroupAndSave
	 * @param pMessageFromSrv: message receive from srv
	 * @return boolean: true if succeeded 
	 */
	static public boolean parseJsonFromCreateGroupAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("community");
			//get the user information from the JSON array
			String pName = lUserArray.getJSONObject(0).getString("name");
			String pId = lUserArray.getJSONObject(0).getString("id");
			String pIdChief = lUserArray.getJSONObject(0).getString("chiefId");
			String pChiefName = lUserArray.getJSONObject(0).getString("chiefName");
			String pDate = lUserArray.getJSONObject(0).getString("creationDate");

			return true;			
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromCreateGroupAndSave exception : "+e.getMessage());
		}
		return false;
	} 

	/**
	 * parseJsonForQuitGroup : parse Json Object to leave a community
	 * @param pCommunity : Community to leave
	 * @return : Json object in string format
	 */
	static public String parseJsonForQuitGroup(Community pCommunity){
		try	{
			//Create json object from user
			JSONObject jUser = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//parse the user in Json object (just id required)
			jUser.put("id", "");			
			jsonObj.put("user",jUser);
			//parse the community in Json object
			jCommunity.put("id", pCommunity.getid());
			jsonObj.put("community",jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCreateGroup exception : "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromQuitGroupAndSave : get information from server for quit community request 
	 * @param pMessageFromSrv : message from server
	 * @return true if done correctly
	 */
	static public boolean parseJsonFromQuitGroupAndSave(String pMessageFromSrv) {
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("community");
			//get the user information from the JSON array
			String pId = lUserArray.getJSONObject(0).getString("id");

			return true;			
		}
		catch (Exception e)	{
			Logger.error("JsonParser parseJsonFromCreateGroupAndSave exception : "+e.getMessage());
		}
		return false;
	}

	/**
	 * parseJsonForUpdateGroup : update community is to change the community name
	 * @param pName : new name for community
	 * @return String Json object to send 
	 */
	static public String parseJsonForUpdateGroup(String pName) {
		try	{
			//Create json object from user
			JSONObject jUser = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//parse the user in Json object (just id required)
			jUser.put("id", "");			
			jsonObj.put("user",jUser);
			//parse the community in Json object
			jCommunity.put("id", "");
			jCommunity.put("name", pName);
			jCommunity.put("idChief", "");
			jsonObj.put("community",jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCreateGroup exception : "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromUpdateGroupAndSave : parse message from server to know if all is alright
	 * @param pMessageFromSrv : message receive from server
	 * @return true if update done
	 */
	static public boolean parseJsonFromUpdateGroupAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("community");
			//get the user information from the JSON array
			String pName = lUserArray.getJSONObject(0).getString("name");
			String pId = lUserArray.getJSONObject(0).getString("id");
			String pIdChief = lUserArray.getJSONObject(0).getString("chiefId");
			String pChiefName = lUserArray.getJSONObject(0).getString("chiefName");
			String pDate = lUserArray.getJSONObject(0).getString("creationDate");

			//Save it for the next requests
			return true;		
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromCreateGroupAndSave exception : "+e.getMessage());
		}
		return false;
	}
	
	/**
	 * parseJsonForGetGroups : Get all communities for the log user
	 * @return JsonObject on string format
	 */
	static public String parseJsonForGetGroups(){
		try	{
			JSONObject jUser = new JSONObject();
			JSONObject jobj = new JSONObject();
			jUser.put("id", "");//put the user id
			jobj.put("user", jUser);
			return jobj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetGroups exception "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromGetGroups : Parse all the communities and save it
	 * @param pMessageFromSrv : JsonObject that contains communities
	 * @return true if no error
	 */
	static public java.util.List<Community> parseJsonFromGetGroups(String pMessageFromSrv){
		try	{
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lCommunityArray = jObject.getJSONArray("communities");
			JSONObject lCommunityObject;
			LinkedList<Community> lCommunityList = new LinkedList<Community>();
			//get all datas for each community 
			for(int i=0; i< lCommunityArray.length(); i++)
			{
				//get the user information from the JSON object
				lCommunityObject = lCommunityArray.getJSONObject(i);
				String pCommunityId = lCommunityObject.getString("id");
				String pCommunityName = lCommunityObject.getString("name");
				String pCommunityChief = lCommunityObject.getString("chiefId");
				String pCommunityChiefName = lCommunityObject.getString("chiefName");
				//String pCommunityDate = lCommunityObject.getString("date");
				lCommunityList.add(new Community(pCommunityId, pCommunityChief, pCommunityName, pCommunityChiefName, null));
			}

			//save it for the next requests
			return lCommunityList;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetGroups exception "+e.getMessage());
		}
		return null;
	}
	
	/**
	 * parseJsonForGroupUpdateNotif
	 * @param isEnable
	 * @return
	 */
	static public String parseJsonForGroupUpdateNotif(boolean isEnable){
		try {
			JSONObject jUser = new JSONObject();
			JSONObject jGroup = new JSONObject();
			JSONObject jobj = new JSONObject();
			jUser.put("idUser", "");//put the user id
			jGroup.put("idGroup", "");//put the user id
			jGroup.put("isNotification", isEnable);//put the user id			
			jobj.put("user", jUser);
			jobj.put("group", jGroup);
			return jobj.toString();
		} catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetGroups exception "+e.getMessage());
		}
		return null;
	}
	
	static public boolean parseJsonFromGroupUpdateNotif(boolean isEnable){
		try {
			//not use for now
			return true;
		} catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetGroups exception "+e.getMessage());
		}
		return false;
	}

	/************************************************************************************************/
	/******************************************** NOTE **********************************************/
	/************************************************************************************************/
	/**
	 * parseJsonForGetNotesFromGroup : create the message to get notes 
	 * @return string : message to send to the server in Json form
	 */
	static public String parseJsonForGetNotesFromGroup(){
		try	{
			//Create json object from user
			JSONObject jUser = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//parse the user in Json object (just id required)
			jUser.put("id", "");			
			jsonObj.put("user",jUser);
			//parse the community in Json object
			jCommunity.put("id", "");
			jsonObj.put("community",jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetNotesForGroup exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromGetNotesAndSave
	 * @param pMessageFromSrv: message receive from srv
	 * @return boolean: true if succeeded 
	 */
	static public boolean parseJsonFromGetNotesAndSave(String pMessageFromSrv){
		try	{
			Note lNote;
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			JSONArray lNotesArray = jObject.getJSONArray("notes");
			LinkedList<Note> lNotesList = new LinkedList<Note>();
			
			//get datas for each note
			for(int i=0; i< lNotesArray.length(); i++){				
				JSONObject jNote = lNotesArray.getJSONObject(i);
				String idNote = jNote.getString("id");
				String idUser = jNote.getString("idUser");
				String authorName= jNote.getString("creatorName");
				String idCommunity = jNote.getString("idGroup");
				String body = jNote.getString("body");
				String creationDate = jNote.getString("creationDate");
				String idChecker = jNote.getString("idChecker");
				String checkerName = jNote.getString("checkerName");
				String checkDate = jNote.getString("checkDate");

				int noteType = jNote.getInt("type");
				if(noteType==Constant.NOTE_TYPE_TODO)//check the type of this note
				{
					boolean isCheck;
					if(jNote.getString("isCheck").equals("1"))
						isCheck = true;
					else 
						isCheck = false;
					lNote = new NoteTodo(idNote, idUser, idChecker, idCommunity, authorName, checkerName, body, isCheck, creationDate, checkDate);//create note from info server
				}
				else
					lNote = new NoteInfo(idNote, idUser, idCommunity, authorName, body, creationDate);//create note from info server
				lNotesList.add(lNote);
			}

			//Save it for the next requests
			//SessionObjects.setListNotes(lNotesList);
			return true;			
		}catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetNotesAndSave exception : "+e.getMessage());
		}
		return false;
	} 

	/**
	 * parseJsonForCreateNotes : create Json object to send to the server
	 * @param pNote note to send to the server
	 * @return String, json to send 
	 */
	static public String parseJsonForCreateNote(Note pNote){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jNote = new JSONObject();

			//Parse the note in Json Object
			jNote.put("body", pNote.get_Body());
			jNote.put("idUser", "");
			jNote.put("idGroup", "");
			if(pNote.getClass().equals(NoteInfo.class))
				jNote.put("type", Constant.NOTE_TYPE_INFO);
			else
				jNote.put("type", Constant.NOTE_TYPE_TODO);
			jNote.put("isCheck", false);//always set false for creation note (even if note is not info)
			jsonObj.put("note", jNote);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCreateNote exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromCreateNoteAndSave : Receive Note created from server
	 * 									parse it and save into our notesList
	 * @param pMessage
	 * @return true if all correct
	 */
	static public boolean parseJsonFromCreateNoteAndSave(String pMessage) {
		try	{
			Note lNote;
			//get information note
			JSONObject jObject = new JSONObject(pMessage);
			JSONArray jNoteArray = jObject.getJSONArray("note");
			JSONObject jNote = jNoteArray.getJSONObject(0);
			String idNote = jNote.getString("id");
			String idUser = jNote.getString("idUser");
			String idCommunity = jNote.getString("idGroup");
			String body = jNote.getString("body");
			String creationDate = jNote.getString("creationDate");
			String authorName= jNote.getString("creatorName");
			int noteType = jNote.getInt("type");	

			//check the note type
			if(noteType==Constant.NOTE_TYPE_TODO){
				//we are the creator and we can't check a note during the creation step
				/*boolean isCheck;				
				String idChecker = jNote.getString("idChecker");
				String checkDate = jNote.getString("checkDate");
				String checkerName= jNote.getString("checkerName");
				if(jNote.getString("isCheck").equals("1"))
					isCheck = true;
				else 
					isCheck = false;*/
				lNote = new NoteTodo(idNote, idUser, null, idCommunity, authorName, null, body, false, creationDate, null);//create note from info server
			}else
				lNote = new NoteInfo(idNote, idUser, idCommunity, authorName, body, creationDate);//create note from info server

			//SessionObjects.getListNotes().addFirst(lNote);//save note in our ListNotes
			return true;
		}catch (Exception e) {
			Logger.error("JsonParser parseJsonFromCreateNoteAndSave exception : "+e.getMessage()+" "+e.getCause()+" "+e.getLocalizedMessage() );
		}
		return false;
	} 

	/**
	 * parseJsonForUpdateNote : Update Note is check it
	 * @param pNote : note to check
	 * @return JsonObject in string format
	 */
	static public String parseJsonForUpdateNote(Note pNote){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jNote = new JSONObject();
			JSONObject jUser = new JSONObject(); 

			//Parse the note in Json Object
			jNote.put("id", pNote.get_id());
			jNote.put("body", pNote.get_Body());
			jNote.put("idUser", "");
			jNote.put("idGroup", "");
			if(pNote.getClass().equals(NoteInfo.class))
				jNote.put("type", Constant.NOTE_TYPE_INFO);
			else
				jNote.put("type", Constant.NOTE_TYPE_TODO);

			jNote.put("isCheck", ((NoteTodo)pNote).get_isCheck());

			//create the user 
			jUser.put("id", "");
			jsonObj.put("user", jUser);
			jsonObj.put("note", jNote);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForUpdateNote exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromUpdateNoteAndSave : Save the updated note receive from server
	 * @param pMessage : message receive from server
	 * @return true if all correct
	 */
	static public boolean parseJsonFromUpdateNoteAndSave(String pMessage){
		try	{
			Note lNote;
			//create JSON object
			JSONObject jObject = new JSONObject(pMessage);
			JSONArray jNoteArray = jObject.getJSONArray("note");
			JSONObject jNote = jNoteArray.getJSONObject(0);
			//get info
			String idNote = jNote.getString("id");
			String idUser = jNote.getString("idUser");
			String idCommunity = jNote.getString("idGroup");
			String body = jNote.getString("body");
			String creationDate = jNote.getString("creationDate");
			String idChecker = jNote.getString("idChecker");
			String checkDate = jNote.getString("checkDate");
			String authorName= jNote.getString("creatorName");
			String checkerName= jNote.getString("checkerName");
			int noteType = jNote.getInt("type");			
			if(noteType==Constant.NOTE_TYPE_TODO)//find the note type
			{
				boolean isCheck;
				if(jNote.getString("isCheck").equals("1"))
					isCheck = true;
				else 
					isCheck = false;
				lNote = new NoteTodo(idNote, idUser, idChecker, idCommunity, authorName, checkerName, body, isCheck, creationDate, checkDate);//create note from info server
			}
			else
				lNote = new NoteInfo(idNote, idUser, idCommunity, authorName, body, creationDate);//create note from info server
			return true;//SessionObjects.updateNote(lNote);
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromUpdateNoteAndSave exception : "+e.getMessage());
		}
		return false;
	} 

	/**
	 * parseJsonForDeleteNote : Create json object for delete note
	 * @param pNote : note to delete
	 * @return Jsonobject as string
	 */
	static public String parseJsonForDeleteNote(Note pNote){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jNote = new JSONObject();
			JSONObject jUser = new JSONObject(); 

			//Parse the note in Json Object
			jNote.put("id", pNote.get_id());
			jNote.put("idUser", "");
			jNote.put("idGroup", "");
			//create the user 
			jUser.put("id", "");
			jsonObj.put("user", jUser);
			jsonObj.put("note", jNote);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDeleteNote exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromDeleteNoteAndSave : get the note deleted
	 * @param pMessage : note deleted as string
	 * @return true if all done
	 */
	static public boolean parseJsonFromDeleteNoteAndSave(String pMessage){
		try	{
			//get information note
			JSONObject jObject = new JSONObject(pMessage);
			JSONArray jNoteArray = jObject.getJSONArray("note");
			JSONObject jNote = jNoteArray.getJSONObject(0);
			String idNote = jNote.getString("id");

			return true;//SessionObjects.deleteNote(idNote);
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromDeleteNoteAndSave exception : "+e.getMessage());
		}
		return false;
	}


	/************************************************************************************************/
	/****************************************** LIST ***********************************************/
	/************************************************************************************************/
	/**
	 * parseJsonForGetListsFromGroup : create jsonobject for get Lists
	 * @return string: represent the Json object
	 */
	static public String parseJsonForGetListsFromGroup(){
		try	{
			//Create json object from user
			JSONObject jUser = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//parse the user in Json object (just id required)
			jUser.put("id", "");			
			jsonObj.put("user",jUser);
			//parse the community in Json object
			jCommunity.put("id", "");
			jsonObj.put("community",jCommunity);
			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetListsFromGroup exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromGetListsAndSave 
	 * @param pMessageFromSrv: message receive from srv
	 * @return boolean: true if succeeded 
	 */
	static public boolean parseJsonFromGetListsAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			JSONArray lListsArray = jObject.getJSONArray("lists");
			LinkedList<Liste> lListList = new LinkedList<Liste>();
			for(int i=0; i< lListsArray.length(); i++)
			{				
				JSONObject jList = lListsArray.getJSONObject(i);
				String idList = jList.getString("id");
				String idUser = jList.getString("idUser");
				String idCommunity = jList.getString("idGroup");
				String name = jList.getString("name");
				String creationDate = jList.getString("creationDate");
				String creatorName = jList.getString("creatorName");
				lListList.add(new Liste(idList, idUser, idCommunity, name, creatorName, creationDate));
			}
			//Save it for the next requests
			//SessionObjects.setListLists(lListList);
			return true;			
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetListsAndSave exception : "+e.getMessage());
		}
		return false;
	} 


	/**
	 * parseJsonForCreateList : create Json object to send to the server
	 * @param pNote note to send to the server
	 * @return String, json to send 
	 */
	static public String parseJsonForCreateList(Liste pList)	{
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jList = new JSONObject();

			//Parse the note in Json Object
			jList.put("name", pList.getName());
			jList.put("idUser", "");
			jList.put("idGroup", "");
			jsonObj.put("list", jList);
			return jsonObj.toString();
		}
		catch (Exception e) 
		{
			Logger.error("JsonParser parseJsonForCreateList exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromCreateListAndSave : Receive List created from server
	 * 									parse it and save into our ListLists
	 * @param pMessage
	 * @return true if parsing ok
	 */
	static public boolean parseJsonFromCreateListAndSave(String pMessage){
		try	{
			JSONObject jObject = new JSONObject(pMessage);
			JSONArray jListArray = jObject.getJSONArray("list");
			JSONObject jList = jListArray.getJSONObject(0);
			String idNote = jList.getString("id");
			String idUser = jList.getString("idUser");
			String idCommunity = jList.getString("idGroup");
			String name = jList.getString("name");
			String creationDate = jList.getString("creationDate");
			String creatorName = jList.getString("creatorName");
			//SessionObjects.getListLists().addFirst(new Liste(idNote, idUser, idCommunity, name, creatorName, creationDate));

			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromCreateListAndSave exception : "+e.getMessage());
		}
		return false;
	} 

	/**
	 * parseJsonForDeleteList : parse list & user to send the correct info to the server to delete a list
	 * @param pList : list to delete
	 * @return String json obj 
	 */
	static public String parseJsonForDeleteList(Liste pList)
	{
		try
		{
			JSONObject jList = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jObj = new JSONObject();

			jList.put("id", pList.get_id());
			jList.put("idCreator", pList.get_idAuthor());
			jUser.put("id", "");

			jObj.put("list", jList);
			jObj.put("user", jUser);
			return jObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDeleteList exception : "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromDeleteList : Receive the list to delete from server and delete it
	 * @param pMessage : message from the server to parse on a Json object
	 * @return true if correctly deleted
	 */
	static public boolean parseJsonFromDeleteListAndSave(String pMessage){
		try	{
			JSONObject jObject = new JSONObject(pMessage);
			JSONArray jListArray = jObject.getJSONArray("list");
			JSONObject jList = jListArray.getJSONObject(0);
			String idList = jList.getString("id");
			//SessionObjects.deleteList(idList);

			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromDeleteListAndSave exception : "+e.getMessage());
		}
		return false;
	}


	/************************************************************************************************/
	/****************************************** ITEM ************************************************/
	/************************************************************************************************/
	/**
	 * parseJsonForGetItems : create json object to get all items for a specific list 
	 * @return Jsonobject as string
	 */
	static public String parseJsonForGetItems(){
		try	{
			//Create json object from user
			JSONObject jList = new JSONObject();
			JSONObject jsonObj = new JSONObject();

			//parse the user in Json object (just id required)
			jList.put("id", "");			
			jsonObj.put("list",jList);
			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetItems exception : "+e.getMessage());
		}
		return "";
	} 

	/**
	 * parseJsonFromGetItemsAndSave
	 * @param pMessageFromSrv: message receive from srv
	 * @return boolean: true if succeeded 
	 */
	static public boolean parseJsonFromGetItemsAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			LinkedList<ItemList> lItemsList = new LinkedList<ItemList>();
			JSONArray lItemsArray = jObject.getJSONArray("items");
			for(int i=0; i< lItemsArray.length(); i++){				
				JSONObject jItem = lItemsArray.getJSONObject(i);
				String idItem = jItem.getString("id");
				String idUser = jItem.getString("idUser");
				String idList = jItem.getString("idList");
				String body = jItem.getString("body");
				String creationDate = jItem.getString("creationDate");
				String isCheck = jItem.getString("isCheck");
				
				ItemList lItem = null;
				if(isCheck.equalsIgnoreCase("0"))
					lItem = new ItemList(idItem, idList, idUser, body, creationDate, false);
				else if(isCheck.equalsIgnoreCase("1"))
					lItem = new ItemList(idItem, idList, idUser, body, creationDate, true);	
				lItemsList.add(lItem);
			}
			//Save it for the next requests
			//SessionObjects.setListItems(lItemsList);
			return true;			
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetItemsAndSave exception : "+e.getMessage());
		}
		return false;
	} 

	/**
	 * parseJsonForCreateItem
	 * @param pItem : item to add
	 * @return String : Json Object 
	 */
	static public String parseJsonForCreateItem(ItemList pItem){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jItem = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//Parse the item in Json Object
			jItem.put("body", pItem.get_Body());
			jItem.put("idUser", "");
			jItem.put("idList", "");//SessionObjects.getCurrentList().get_id());
			jsonObj.put("item", jItem);
			jCommunity.put("idList",""); 
			jsonObj.put("comunity", jCommunity);
			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCreateItem exception "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromCreateItemAndSave
	 * @param pMessageFromSrv
	 * @return boolean : true if correctly saved 
	 */
	static public boolean parseJsonFromCreateItemAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			JSONArray lItemsArray = jObject.getJSONArray("item");
			JSONObject jItem = lItemsArray.getJSONObject(0);
			String idItem = jItem.getString("id");
			String idUser = jItem.getString("idUser");
			String idList = jItem.getString("idList");
			String body = jItem.getString("body");
			String creationDate = jItem.getString("creationDate");
			//Boolean isCheck = jItem.getBoolean("isCheck"); useless becaus always false when we create an item

			ItemList lItem = new ItemList(idItem, idList, idUser, body, creationDate, false);
			//Save it for the next requests
			//SessionObjects.getListItems().addFirst(lItem);
			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromCreateItemAndSave exception"+e.getMessage());
		}
		return false;
	}

	/**
	 * parseJsonForDeleteItem
	 * @param pItem : item to add
	 * @return String : Json Object 
	 */
	static public String parseJsonForDeleteItem(ItemList pItem){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jItem = new JSONObject();
			JSONObject jUser = new JSONObject();

			//Parse the item in Json Object
			jItem.put("id", pItem.get_id());
			jsonObj.put("item", jItem);
			jUser.put("id",""); 
			jsonObj.put("user", jUser);
			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDeleteItem exception "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromDeleteItemAndSave
	 * @param pMessageFromSrv
	 * @return boolean : true if correctly saved 
	 */
	static public boolean parseJsonFromDeleteItemAndSave(String pMessageFromSrv){
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			JSONArray lItemsArray = jObject.getJSONArray("item");
			JSONObject jItem = lItemsArray.getJSONObject(0);
			String idItem = jItem.getString("id");

			//Save it for the next requests
			//SessionObjects.deleteItem(idItem);
			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromDeleteItemAndSave exception"+e.getMessage());
		}
		return false;
	}

	/**
	 * parseJsonForCheckItem : create Json object for check/uncheck item
	 * @param pItem : item to check/uncheck
	 * @return : JsonObject as String 
	 */
	public static String parseJsonForCheckItem(ItemList pItem){
		try	{
			JSONObject jObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jItem = new JSONObject();
			
			//set the user
			jUser.put("id", "");
			jObj.put("user", jUser);
			//set the item
			jItem.put("id", pItem.get_id());
			jItem.put("isCheck", pItem.get_IsCheck());
			jObj.put("item", jItem);
			
			return jObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCheckItem exception "+e.getMessage());
		}		
		return null;
	}
	
	/**
	 * parseJsonFromCheckItem : 
	 * @param pItem : item checked/unchecked
	 * @return
	 */
	public static boolean parseJsonFromCheckItem(String pMessageFromSrv){
		try	{
			//item":{"id":"1","idUser":"3","idList":"23","body":"message","creationDate":"12/12/12","isCheck":"true"}}
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the info
			JSONArray lItemsArray = jObject.getJSONArray("item");
			JSONObject jItem = lItemsArray.getJSONObject(0);
			String idItem = jItem.getString("id");
			String idUser = jItem.getString("idUser");
			String idList = jItem.getString("idList");
			String body = jItem.getString("body");
			String creationDate = jItem.getString("creationDate");
			String isCheck = jItem.getString("isCheck");
			ItemList lItem = null;
			if(isCheck.equalsIgnoreCase("0"))
				lItem = new ItemList(idItem, idList, idUser, body, creationDate, false);
			else if(isCheck.equalsIgnoreCase("1"))
				lItem = new ItemList(idItem, idList, idUser, body, creationDate, true);	

			return true;//SessionObjects.checkItem(lItem);
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForCheckItem exception "+e.getMessage());
		}		
		return false;
	}
	/************************************************************************************************/
	/*************************************** INVITE USER ********************************************/
	/************************************************************************************************/

	/**
	 * parseJsonForInviteUser create json object to invite a person
	 * @param Mail of user to invite ine the current group
	 * @return Jsonobject as string
	 */
	static public String parseJsonForInviteUser(String pMail){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jInvite = new JSONObject();
			JSONObject jCommunity = new JSONObject();

			//Parse the note in Json Object
			jUser.put("id", "");
			jsonObj.put("user", jUser);
			jInvite.put("mail", pMail);
			jsonObj.put("invite", jInvite);
			jCommunity.put("id","");
			jCommunity.put("name",""); 
			jsonObj.put("community", jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForInviteUser exception "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromInviteUser get message from InViteUser
	 * 						do nothing in this version because response is by mail
	 * @param pMessageFromSrv message receive from server
	 * @return true il all done
	 */
	static public boolean parseJsonFromInviteUser(String pMessageFromSrv){
		try	{
			//Nothing to do 
			//If return code is 200 the invite mail has been send
			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromInviteUser exception "+e.getMessage());
		}
		return false;
	}

	/**
	 * parseJsonForGetUsers create json object to get users of a group
	 * @return JsonObject as string
	 */
	static public String parseJsonForGetUsers(){
		try	{
			JSONObject jsonObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jCommunity = new JSONObject();
			//create the Json user
			jUser.put("id", "");
			jsonObj.put("user", jUser);
			//create the Community Json 
			jCommunity.put("id", "");
			jsonObj.put("community", jCommunity);

			return jsonObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetUsers exception "+e.getMessage());
		}
		return null;
	}

	/**
	 * parseJsonFromGetUsers parse users info from server 
	 * @param pMessageFromSrv Message receive from server
	 * @return true if all done
	 */
	static public boolean parseJsonFromGetUsers(String pMessageFromSrv)	{
		try	{
			//Get the JSON Object from the message
			JSONObject jObject = new JSONObject(pMessageFromSrv);

			//get the user in the JSON object
			JSONArray lUserArray = jObject.getJSONArray("users");
			LinkedList<User> lUsersList = new LinkedList<User>();
			for(int i=0; i< lUserArray.length(); i++){
				//get the user information from the JSON object
				String pName = lUserArray.getJSONObject(i).getString("name");
				String pMail = lUserArray.getJSONObject(i).getString("mail");
				String pId = lUserArray.getJSONObject(i).getString("id");
				lUsersList.add(new User(pId, pMail, pName, null));
			}
			//save it for the next requests
			//SessionObjects.setListUsers(lUsersList);
			
			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetUsers exception "+e.getMessage());
		}
		return false;
	}
	
	
	/************************************************************************************************/
	/**************************************** BILL **************************************************/
	/************************************************************************************************/
	
	/**
	 * parseJsonForCreateBill create json object to send to server to create a bill
	 * @param Bill to create
	 * @return JsonObject as string
	 */
	static public String parseJsonForCreateBill(Bill pBill) 
	{
		try
		{
			//create JsonObject
			JSONObject jObj = new JSONObject();
			JSONObject jBill = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONArray jUsersInBill = new JSONArray();
			
			//set the bill object
			jBill.put("idAuthor", "");
			jBill.put("idGroup", "");
			jBill.put("value", pBill.get_Value());
			jBill.put("name", pBill.getName());
			
			//set the added users 
			for(int i=0; i<pBill.get_Users().size(); i++)
			{
				jUser.put("id", pBill.get_Users().get(i).getId());
				jUsersInBill.put(jUser);
				jUser = new JSONObject();
			}
			jObj.put("bill", jBill);
			jObj.put("usersInBill", jUsersInBill);
			return jObj.toString();
		}
		catch(Exception e)
		{
			Logger.error("JsonParser parseJsonForCreateBill exception " +e.getMessage());
		}	
		return null;
	}
	
	/**
	 * parseJsonFromCreateBillAndSaveIt Get bill info created on server
	 * @param pMessageFromSrv message receive from server
	 * @return true if all done
	 */
	static public boolean parseJsonFromCreateBill(String pMessageFromSrv) 
	{
		try
		{
			/*JSONObject jObj = new JSONObject(pMessageFromSrv);
			JSONObject jBill = jObj.getJSONObject("bill");
			
			//get the values
			String lId = jBill.getString("id");
			String lIdAuthor = jBill.getString("idAuthor");
			String lIdGroup = jBill.getString("idGroup");
			String lValue = jBill.getString("value");
			String lName = jBill.getString("name");
			String lDate = jBill.getString("creationDate");*/
			
			//Bill lBill = new Bill(lId, lIdAuthor, lIdGroup, lDate, lValue, lName);
			//we get the dashobard so we parse it
			return parseJsonFromGetDashboard(pMessageFromSrv);
			
		}
		catch(Exception e)
		{
			Logger.error("JsonParser parseJsonFromCreateBill exception " +e.getMessage());
		}	
		return false;
	}
	
	/**
	 * parseJsonForGetDashboard : create json object to get Bill dashboard from server 
	 * @return jsonObject as string
	 */
	static public String parseJsonForGetDashboard()
	{
		try
		{
			//create json object
			JSONObject jObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jCommunity = new JSONObject();
			
			jUser.put("id", "");
			jCommunity.put("id", "");
			jObj.put("user", jUser);
			jObj.put("community", jCommunity);
			
			return jObj.toString();
			
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetDashboard exception "+e.getMessage());
		}
		return null;
	}
	
	/**
	 * parseJsonFromGetDashboard : get the dashboard datas from server
	 * @param pMessageFromSrv : message receive from server
	 * @return true if all done
	 */
	static public boolean parseJsonFromGetDashboard(String pMessageFromSrv)
	{
		try
		{
			//create json object
			JSONObject jObj = new JSONObject(pMessageFromSrv);
			JSONObject jDashBoard = jObj.getJSONObject("dashboard");
			String lSolde = jDashBoard.getString("solde");
			String lCredit = jDashBoard.getString("credit");
			JSONArray jArray = jDashBoard.getJSONArray("array");
			LinkedList<PaymentByUser> lListPayment = new LinkedList<PaymentByUser>();
			//get all the payment
			for(int i=0; i<jArray.length(); i++)
			{
				//add payment 
				lListPayment.add(new PaymentByUser(jArray.getJSONObject(i).getString("value"), jArray.getJSONObject(i).getString("idUserReceiver"), 
						jArray.getJSONObject(i).getString("name"), jArray.getJSONObject(i).getString("idUserPaying")));
			}			
			
			//save info
			/*SessionObjects.setInput(lSolde);
			SessionObjects.setOutput(lCredit);	
			SessionObjects.setListPayement(lListPayment);*/		
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetDashboard exception "+e.getMessage());
		}
		return false;
	}
	
	/**
	 * parseJsonForPayBill : create JsonObject for pay bill
	 * @param pPayment payment to pay
	 * @return JsonObject as String
	 */
	static public String parseJsonForPayBill(PaymentByUser pPayment)
	{
		try
		{//{"repayment":{"idUserPaying":"59", "idUserReceiver":"49","value":"151,2","idGroup":"11"}}
			JSONObject jObj = new JSONObject();
			JSONObject jRepayment = new JSONObject();
			//set the repayment
			jRepayment.put("idUserPaying", pPayment.get_idUserPayer());
			jRepayment.put("idUserReceiver", pPayment.get_idReceiver());
			jRepayment.put("value", pPayment.get_value());
			jRepayment.put("idGroup", "");
			
			jObj.put("repayment", jRepayment);
			return jObj.toString();			
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForPayBill exception "+e.getMessage());
		}
		return null;
	}
		
	/**
	 * parseJsonFromPayBill : parse data from server
	 * @param pMessageFromSrv
	 * @return
	 */
	static public boolean parseJsonFromPayBill(String pMessageFromSrv)
	{
		//we get the dashboard in response
		return parseJsonFromGetDashboard(pMessageFromSrv);
	}
	
	/**
	 * parseJsonForGetBills : create JsonObject to send to server to get bills
	 * @return Jsonobject as string
	 */
	static public String parseJsonForGetBills()
	{
		try
		{
			//create json object
			JSONObject jObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jCommunity = new JSONObject();
			//put datas
			jUser.put("id", "");
			jCommunity.put("id", "");
			jObj.put("user", jUser);
			jObj.put("community", jCommunity);
			return jObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForGetBills exception "+e.getMessage());
		}
		return "";
	}
	
	/**
	 * parseJsonFromGetBills : parse json object receive from server
	 * @param pMessageFromSrv : message receive from server
	 * @return true il all done
	 */
	static public boolean parseJsonFromGetBills(String pMessageFromSrv)
	{
		try
		{
			// * {"value":200,"bills":[{"id":"60","idAuthor":"59","idGroup":"11",
			//"creationDate":"2011-01-17 23:56:31","value":"120","name":"test","authorName":"test2 Name :)","nbUser":"2"}]}
			LinkedList<Bill> lListBill = new LinkedList<Bill>();
			
			//create json object
			JSONObject jObj = new JSONObject(pMessageFromSrv);
			JSONObject jTemp;
			JSONArray jBills = jObj.getJSONArray("bills");
			for(int i=0; i< jBills.length(); i++)
			{
				jTemp = jBills.getJSONObject(i);
				lListBill.add(new Bill(jTemp.getString("id"), jTemp.getString("idAuthor"), jTemp.getString("idGroup"), jTemp.getString("creationDate"),
						jTemp.getString("value"), jTemp.getString("name"), jTemp.getString("authorName"), jTemp.getInt("nbUser")));	
			}
			
			//save info
			//SessionObjects.setListBills(lListBill);				
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromGetBills exception "+e.getMessage());
		}
		return false;
	}
	
	/**
	 * parseJsonForDeleteBills : create json object to delete bill to send to server
	 * @param pBill : bill to send
	 * @return JJson object as string
	 */
	static public String parseJsonForDeleteBills(Bill pBill)
	{
		try
		{
			//{"bill":{"id":"3"}, "user":{"id":"12"}}
			JSONObject jObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONObject jBill = new JSONObject();
			
			jUser.put("id", "");
			jBill.put("id", pBill.get_id());
			jObj.put("user", jUser);
			jObj.put("bill", jBill);
			
			return jObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDeleteBills exception "+e.getMessage());
		}
		return "";
	}
	
	/**
	 * parseJsonFromDeleteBills : parse json from server when bill deleted
	 * @param pMessageFromSrv : message receive from server
	 * @return true if all done
	 */
	static public boolean parseJsonFromDeleteBills(String pMessageFromSrv)	{
		try	{
			//{"value":200,"bill":{"id":"3"}}
			JSONObject jObject = new JSONObject(pMessageFromSrv);
			JSONObject jBill = jObject.getJSONObject("bill");
			String lBillId = jBill.getString("id");
			
			//SessionObjects.deleteBill(lBillId);
			return true;
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonFromDeleteBills exception "+e.getMessage());
		}
		return false;
	}


	public static String parseJsonForCdmRegistration(String pToken) {
		try	{
			//{"bill":{"id":"3"}, "user":{"id":"12"}}
			JSONObject jObj = new JSONObject();
			JSONObject jUser = new JSONObject();
			
			jUser.put("id", "");
			jUser.put("deviceToken", pToken);
			jObj.put("user", jUser);
			
			return jObj.toString();
		}
		catch (Exception e) {
			Logger.error("JsonParser parseJsonForDeleteBills exception "+e.getMessage());
		}
		return "";
	}


	public static boolean parseJsonFromCdmRegistration(String pMessage) {
		return true;
	}
}
