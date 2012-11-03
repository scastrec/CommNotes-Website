package models;

/**
 * ItemList
 * @author Stephane
 *
 */
public class ItemList {

	private String _id; //id of the item
	private String _idList; //id of the list where is this item
	private String _idAuthor; //id of the author of this item
	private String _Body; //notes of this item
	private String _CreationDate;//creation date of this item
	//private String _UpdateDate = null;//Update date of this item
	private Boolean _IsCheck = false;
	
	/**
	 * ItemList Ctor : Create an Item to send to server
	 * @param pIdList
	 * @param pIdAuthor
	 * @param pBody
	 */
	public ItemList(String pIdList,String pIdAuthor, String pBody)
	{
		_idAuthor = pIdAuthor;
		_idList = pIdList;
		_Body = pBody;
	}
	
	/**
	 * ItemList Ctor : Create a complete Item. Call when server response
	 * @param pId
	 * @param pIdList
	 * @param pIdAuthor
	 * @param pBody
	 * @param pCreationDate
	 * @param pUpdateDate
	 */
	public ItemList(String pId, String pIdList,String pIdAuthor, String pBody, String pCreationDate, Boolean pCheck)
	{
		_id = pId;
		_idAuthor = pIdAuthor;
		_idList = pIdList;
		_Body = pBody;
		_CreationDate = pCreationDate;
		_IsCheck = pCheck;
	}

	/* Accessors */
	public String get_id() {
		return _id;
	}
	public String get_idList() {
		return _idList;
	}
	public String get_idAuthor() {
		return _idAuthor;
	}
	public String get_Body() {
		return _Body;
	}
	public String get_Date() {
		return _CreationDate; 		
	}
	public Boolean get_IsCheck() {
		return _IsCheck;
	}
	public void set_IsCheck(Boolean pCheck) {
		_IsCheck = pCheck;
	}
	
	@Override
	public String toString()
	{
		return "item "+_Body+" Date "+_CreationDate;
	}
}
