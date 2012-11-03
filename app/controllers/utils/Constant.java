package controllers.utils;
/**
 * Constant : Contain all constant required 
 * @author Stephane
 *
 */
public final class Constant {

	public static final int NOTE_TYPE_INFO=0;
	public static final int NOTE_TYPE_TODO=1;  
	
	//INT Var to recognize different http request
	final static public int REQ_INSCRIPTION = 0;
	final static public int REQ_DESINSCRIPTION = 1;
	final static public int REQ_LOG = 2;
	final static public int REQ_REGISTER_GCM = 3;
	//final static public int REQ_LIST_USERS = 3;//rename as REQ_GROUP_USERS
	
	final static public int REQ_GROUP_CREATE = 4;
	final static public int REQ_GROUP_UPDATE = 5;
	final static public int REQ_GROUP_DELETE = 6;
	final static public int REQ_GROUP_INVITE_USER= 7;
	final static public int REQ_GROUP_RESP_INVITE = 8;
	final static public int REQ_GROUP_QUIT = 9;
	final static public int REQ_GROUPS = 10;
	final static public int REQ_GROUP_USERS = 28;
	final static public int REQ_GROUP_NOTIFICATION = 31;
	
	final static public int REQ_NOTE_CREATE = 11;
	final static public int REQ_NOTE_UPDATE = 12;
	final static public int REQ_NOTE_DELETE = 30;
	final static public int REQ_NOTES = 13;
	
	
	final static public int REQ_LIST_CREATE = 14;
	final static public int REQ_LIST_DELETE = 15;
	final static public int REQ_LIST_UPDATE = 16;
	final static public int REQ_LISTS = 17;
	final static public int REQ_LIST_ITEMS = 18;
	
	final static public int REQ_ITEM_CHECK = 29;//use to check or uncheck
	
	final static public int REQ_BILL_ADD = 19;
	final static public int REQ_BILL_DELETE = 20;
	final static public int REQ_BILL_PAID = 21;
	final static public int REQ_BILL_DASHBOARD = 22;
	final static public int REQ_BILL_USER = 23;
	final static public int REQ_BILLS = 24;
	final static public int REQ_BILL_CREATE = 25;
	
	final static public int REQ_LIST_ITEM_ADD = 26;
	final static public int REQ_LIST_ITEM_DELETE = 27;
	
	
	/************ internal error code ************/
	final static public int ERROR_INPUT_NOT_MAIL = 1000;
	final static public int ERROR_INPUT_NOT_NUMBER = 1001;
	final static public int ERROR_INPUT_PRICE_WRONG = 1002;
	final static public int ERROR_INPUT_EMPTY = 1003;
	final static public int ERROR_INPUT_PWD = 1004;
}
