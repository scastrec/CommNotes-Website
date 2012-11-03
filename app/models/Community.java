package models;

/**
 * Community 
 * @author Stephane
 *
 */
public class Community {

	private String id;
	private String name;
	private String chiefName; 
	private String creationDate;
	private String idChief;

	public Community(String pId, String pIdChief, String pName, String pChiefName, String pDate){
		id = pId;
		idChief = pIdChief;
		name = pName;
		chiefName = pChiefName;
		creationDate = pDate;
	}
	public String getid() {
		return id;
	}
	public String getidChief() {
		return idChief;
	}
	public String getname() {
		return name;
	}
	public void setname(String pName) {
		name = pName;
	}
	public String getchiefName() {
		return chiefName;
	}
	public String getcreationDate() {
		return creationDate;
	}
}
