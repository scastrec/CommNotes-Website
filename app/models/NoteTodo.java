package models;
/**
 * NoteTodo : note that can be check
 * @extends Note
 * @author Stephane
 *
 */
public class NoteTodo extends Note{

	private boolean _isCheck; //true if this notes has been done, false otherwise. Null if that's a classical note
	private String _idChecker; //id of the checker 
	private String _CheckDate;//date of the check
	private String _CheckerName;


	/**
	 * NoteTodo Ctor
	 * @param pIdUser
	 * @param pIdCommunity
	 * @param pBody
	 * @param pIsCheck
	 */
	public NoteTodo(String pIdUser, String pIdCommunity, String pBody, boolean pIsCheck) {
		_idAuthor = pIdUser;
		_idCommunity = pIdCommunity;
		_Body = pBody;
		_isCheck = pIsCheck;
	}
	/**
	 * NoteTodo Ctor : use when create a new note (params receive from serveur) called from JsonParser 
	 * @param pId
	 * @param pIdUser
	 * @param pIdChecker
	 * @param pIdCommunity
	 * @param pAuthorName
	 * @param pCheckerName
	 * @param pBody
	 * @param pIsCheck
	 * @param pCreationDate
	 * @param pCheckDate
	 */
	public NoteTodo(String pId, String pIdUser, String pIdChecker, String pIdCommunity, String pAuthorName, String pCheckerName, String pBody, boolean pIsCheck, String pCreationDate, String pCheckDate) {
		_id = pId;
		_idAuthor = pIdUser;
		_idChecker = pIdChecker;
		_idCommunity = pIdCommunity;
		_Body = pBody;
		_isCheck = pIsCheck;
		_CreationDate = pCreationDate;
		_CheckDate = pCheckDate;
		_AuthorName = pAuthorName;
		_CheckerName = pCheckerName;
	}
	
	public String get_idChecker() {
		return _idChecker;
	}
	public String get_CheckerName() {
		return _CheckerName;
	}
	public boolean get_isCheck() {
		return _isCheck;
	}
	public void set_isCheck(boolean pCheck)
	{
		_isCheck = pCheck;
	}
	public String get_CheckDate() {
		return _CheckDate;
	}
}
