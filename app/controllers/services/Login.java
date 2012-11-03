package controllers.services;

import play.*;
import play.mvc.*;

import views.html.*;
import models.User;
import models.Community;
import controllers.requests.RequestMaker;
import controllers.requests.JsonParser;
import controllers.utils.Constant;
import controllers.utils.ReturnCode;
import controllers.utils.security.AeSimpleSHA1;

import java.util.List;

public class Login {
  
	/**
	 * isUser: check the API to know if user is known
	 *		If user, return communities
	 */
	public static List<Community> isUser(User user){
		try{
			user.setPwd(AeSimpleSHA1.SHA1(user.getPwd()));
			RequestMaker req = new RequestMaker(Constant.REQ_LOG);
			
			if(req.doRequest(user) == ReturnCode.CODE_OK){
				return JsonParser.getCommunities(req.getJson());				
			}				
		} catch(java.security.NoSuchAlgorithmException e){
			Logger.error("error algorithm for pwd",e);
		} catch(Exception ex){
			Logger.error("error isUser",ex);
		} 
		
		return null;
	}
  
}