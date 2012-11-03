package models;

/**
 * NoteInfo : Note that can't be check
 * @extends Note
 */
public class NoteInfo extends Note{

	/**
	 * Note Info Ctor : Called from CommunityNotes to create the object to send to server to add note
	 * @param pIdUser
	 * @param pIdCommunity
	 * @param pBody
	 */
	public NoteInfo(String pIdUser, String pIdCommunity, String pBody) {
			_idAuthor = pIdUser;
			_idCommunity = pIdCommunity;
			_Body = pBody;
	}

	/**
	 * NoteInfo Ctor : Called when we receive the response from server 
	 * @param pId
	 * @param pIdUser
	 * @param pIdCommunity
	 * @param pBody
	 * @param pCreationDate
	 */
	public NoteInfo(String pId, String pIdUser, String pIdCommunity, String pAuthorName,
			String pBody, String pCreationDate) {
		_id = pId;
		_idAuthor = pIdUser;
		_idCommunity = pIdCommunity;
		_Body = pBody;
		_CreationDate = pCreationDate;
		_AuthorName = pAuthorName;
	}

}
