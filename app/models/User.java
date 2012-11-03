package models;

import java.util.Date;
import play.db.ebean.Model;



/**
 * User
 * @author Stephane
 *
 */
public class User extends Model{

	private String _id;
	private String _mail;//@mail of the user
	private String _Name;
	private String _passwd;
	private Date _InscriptionDate;
	private Date _LastConnectedDate;

	/**
	 * User Ctor
	 * @param pId 
	 * @param pMail
	 * @param pName
	 * @param pPwd
	 */
	public User(String pId, String pMail, String pName, String pPwd){
		_id = pId;
		_Name = pName;
		_mail = pMail;
		_passwd = pPwd;
	}
	/**
	 * User Ctor
	 * @param pId
	 * @param pMail
	 * @param pPwd
	 */
	public User(String pId, String pMail, String pPwd){
		_id = pId;
		_mail = pMail;
		_passwd = pPwd;
	}
	/**
	 * User Ctor
	 * @param pMail
	 * @param pPwd
	 */
	public User(String pMail, String pPwd)	{
		_mail = pMail;
		_passwd = pPwd;
	}

	public String getId() {
		return _id;
	}
	public String getName() {
		return _Name;
	}
	public String getPwd() {
		return _passwd;
	}
	public void setPwd(String pwd) {
		_passwd = pwd;
	}
	public Date getInscriptionDate() {
		return _InscriptionDate;
	}
	public Date getLastConnectedDate() {
		return _LastConnectedDate;
	}

	public String getMail() {
		return _mail;
	}
	
	public String toString(){
		return "mail:"+_mail;
	}
}
