package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.RequestBody;

import views.html.*;
import controllers.services.Login;
import models.User;
import models.Community;

import java.util.List; 

public class Application extends Controller {
  
  public static Result index() {
    return ok(login.render("","none"));
  }
  
  /**
   * Login entry point
   */
  public static Result login() {
  	final Map<String, String[]> values = request().body().asFormUrlEncoded();
  	String mail = values.get("mail")[0];
  	String pwd = values.get("pwd")[0];
  	User u = new User(mail,pwd);
  	List<Community> communities = Login.isUser(u);
  	if(communities == null){
  		return badRequest(login.render("User or password incorrect", "block"));
  	} 
  	return ok(index.render(u, communities));
  } 
}