package controllers.utils;
/**
 * ReturnCode : Contain different code that can be send by server
 * @author Stephane
 *
 */
public class ReturnCode {

	//all the different codes that the server can send
	public static final int CODE_OK = 200;
	public static final int CODE_NULL_RETURN= 300;
	public static final int CODE_SRV_NOT_FOUND = 400;
	public static final int CODE_ACCESS_NOT_PERMITED = 500;
	public static final int CODE_USER_ALREADY_EXIST = 600;
	public static final int CODE_OBJECT_NOT_EXIST = 700;
	public static final int CODE_ERROR_SYNTAX_REQ = 800;
	public static final int CODE_UNKNOWN_ERROR= 900;
}
