package models;


import play.db.ebean.Model;

/**
 * List
 * @author Stephane
 *
 */
public class Liste {
	private String _id;//id of this list
	private String _idAuthor;//id of the author
	private String _idCommunity;//id of the Community/community 
	private String _Name;// name of the list
	private String _CreationDate;// date creation of the list
	private String _CreatorName;// name of the creator of the list
	
	/**
	 * List Ctor : use to create a List before sending to server
	 * @param pIdUser
	 * @param pIdCommunity
	 * @param pName
	 */
	public Liste(String pIdAuthor, String pIdCommunity, String pName)
	{
		_idAuthor = pIdAuthor;
		_idCommunity = pIdCommunity;
		_Name = pName;
	}
	
	/**
	 * List Ctor : use when receive response from server
	 * @param pId
	 * @param pIdUser
	 * @param pIdCommunity
	 * @param pName
	 * @param pCreationDate
	 */
	public Liste(String pId, String pIdUser, String pIdCommunity , String pName, String pNameAuthor, String pCreationDate)
	{
		_id = pId;
		_idAuthor = pIdUser;
		_CreatorName = pNameAuthor;
		_idCommunity = pIdCommunity;
		_Name = pName;
		_CreationDate = pCreationDate;
	}

	
	/* Accessors */
	public String get_id() {
		return _id;
	}

	public String get_idAuthor() {
		return _idAuthor;
	}

	public String get_idCommunity() {
		return _idCommunity;
	}
	
	public String getName() {
		return _Name;
	}

	public String get_CreationDate() {
		return _CreationDate;
	}
	public String get_NameAuthor() {
		return _CreatorName;
	}
	
	public void set_Name(String _Name) {
		this._Name = _Name;
	}

}
