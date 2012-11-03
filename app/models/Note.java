package models;
/**
 * Note : abstract because there is two type of not wich extends this class
 */
public abstract class Note {
	
	protected String _id; //id of this note
	protected String _idAuthor; //id of this note's author
	protected String _AuthorName;
	protected String _idCommunity; //id of the community which has this note
	protected String _Body;//text of thise note
	protected String _CreationDate; //date of the creation
	
	public String get_id() {
		return _id;
	}
	
	public String get_idAuthor() {
		return _idAuthor;
	}

	public String get_idCommunity() {
		return _idCommunity;
	}

	public String get_Body() {
		return _Body;
	}

	public String get_CreationDate() {
		return _CreationDate;
	}
	public String get_creatorNote()
	{
		return _AuthorName;
	}
}
