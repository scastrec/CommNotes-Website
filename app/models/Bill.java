package models;
import java.util.LinkedList;

/**
 * Bill : represent bill.
 * @author Stephane
 *
 */
public class Bill {

	private String _id;
	private String _idAuthor;
	private String _idGroup;
	private int _nbUser;
	private String _CreationDate;
	private String _Value;
	private String _Name;
	private String _AuthorName;
	private LinkedList<User> _UserInBill;
	 
	
	/**
	 * Bill Ctor
	 * 		Use when receive bill from server
	 * @param pId : bill's id
	 * @param pIdAuthor : author's id
	 * @param pIdGroup : Group's id
	 * @param pCreationDate : date of bill creation
	 * @param pValue : price/value of the bill
	 * @param pName : name of the bill
	 * @param pUsers : users that participate to the bill
	 */
	public Bill(String pId, String pIdAuthor, String pIdGroup, String pCreationDate, String pValue, String pName, LinkedList<User> pUsers)
	{
		_id = pId;
		_idAuthor = pIdAuthor;
		_idGroup = pIdGroup;
		_CreationDate = pCreationDate;
		_Value = pValue;
		_Name = pName;
		_UserInBill = pUsers;
	}
	
	/**
	 * Ctor : use for display bills
	 * @param pId
	 * @param pIdAuthor
	 * @param pIdGroup
	 * @param pCreationDate
	 * @param pValue
	 * @param pName
	 * @param pAuthorName
	 * @param pNbUser
	 */
	public Bill(String pId, String pIdAuthor, String pIdGroup, String pCreationDate, String pValue, String pName, String pAuthorName, int pNbUser)
	{
		_id = pId;
		_idAuthor = pIdAuthor;
		_idGroup = pIdGroup;
		_CreationDate = pCreationDate;
		_Value = pValue;
		_Name = pName;
		_AuthorName = pAuthorName;
		_nbUser = pNbUser;
	}
	
	/**
	 * Bill Ctor : Use to send Bill to Server
	 * @param pValue : price/value of the bill
	 * @param pName : name of the bill
	 * @param pUsers : users that participate to the bill
	 */
	public Bill(String pValue, String pName, LinkedList<User> pUsers)
	{
		_Value = pValue;
		_Name = pName;
		_UserInBill = pUsers;
	}

	/* Accessor */
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_id() {
		return _id;
	}
	public void set_idAuthor(String _idAuthor) {
		this._idAuthor = _idAuthor;
	}
	public String get_idAuthor() {
		return _idAuthor;
	}
	public void set_idGroup(String _idGroup) {
		this._idGroup = _idGroup;
	}
	public String get_idGroup() {
		return _idGroup;
	}
	public String get_CreationDate() {
		return _CreationDate;
	}
	public void set_Value(String _Value) {
		this._Value = _Value;
	}
	public String get_Value() {
		return _Value;
	}
	public void set_Name(String _Name) {
		this._Name = _Name;
	}
	public String getName() {
		return _Name;
	}
	public LinkedList<User> get_Users() {
		return _UserInBill;
	}

	public void set_AuthorName(String _AuthorName) {
		this._AuthorName = _AuthorName;
	}

	public String get_AuthorName() {
		return _AuthorName;
	}

	public void set_nbUser(int _nbUser) {
		this._nbUser = _nbUser;
	}

	public int get_nbUser() {
		return _nbUser;
	}
}
