package controllers.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * InfosUrl : create a map between REQ and url
 * @author Stephane
 *
 */
public class InfosUrl {
	
	final static private String URL_BASE = "http://comnotes.stephanecastrec.info/";

	final static private String URL_INSCRIPTION=URL_BASE+"/user/inscription.php?";
	final static private String URL_DESINSCRIPTION=URL_BASE+"/user/desinscription.php?";
	
	final static private String URL_LOG=URL_BASE+"/user/log.php?";
	final static private String URL_CDM_REGISTER=URL_BASE+"/user/cdmRegistration.php?";
		
	final static private String URL_GROUP_CREATE=URL_BASE+"/group/create.php?";
	final static private String URL_GROUP_UPDATE=URL_BASE+"/group/update.php?";
	final static private String URL_GROUP_DELETE=URL_BASE+"/group/delete.php?";
	final static private String URL_GROUP_INVITE_USER=URL_BASE+"/group/inviteUser.php?";
	//final static private String URL_GROUP_RESP_INVITE=URL_BASE+"/group/responseInvite.php?"; Useless because response are in mail not in this apps
	final static private String URL_GROUP_QUIT=URL_BASE+"/group/quit.php?";
	final static private String URL_GROUP_USERS=URL_BASE+"/group/users.php?";
	final static private String URL_GROUPS=URL_BASE+"/groups.php?";
	final static private String URL_GROUP_PUSH=URL_BASE+"/group/managepush.php?";

	
	final static private String URL_NOTE_CREATE=URL_BASE+"/note/create.php?";
	final static private String URL_NOTE_CHECK=URL_BASE+"/note/check.php?";//check or uncheck note
	final static private String URL_NOTE_DELETE=URL_BASE+"/note/delete.php?";
	final static private String URL_NOTES=URL_BASE+"/notes.php?";

	
	final static private String URL_LIST_CREATE=URL_BASE+"/list/create.php?";
	final static private String URL_LIST_UPDATE=URL_BASE+"/list/update.php?";
	final static private String URL_LISTS=URL_BASE+"/lists.php?";
	final static private String URL_LIST_ITEMS=URL_BASE+"/list/items.php?";
	final static private String URL_LIST_DELETE=URL_BASE+"/list/delete.php?";
	final static private String URL_LIST_ITEM_CREATE=URL_BASE+"/list/item/create.php?";
	final static private String URL_LIST_ITEM_DELETE=URL_BASE+"/list/item/delete.php?";
	final static private String URL_LIST_ITEM_CHECK=URL_BASE+"/list/item/check.php?";
	
	final static private String URL_BILL_CREATE=URL_BASE+"/bill/create.php?";
	final static private String URL_BILL_DELETE=URL_BASE+"/bill/delete.php?";
	final static private String URL_BILL_PAID=URL_BASE+"/bill/repayment.php?";
	final static private String URL_BILL_DASHBOARD=URL_BASE+"/bill/dashboard.php?";
	final static private String URL_BILLS=URL_BASE+"/bills.php?";
	final static private String URL_BILLS_USER=URL_BASE+"/billsUser.php?";
		
	
	public static Map<Integer, String> UrlMap = new HashMap<Integer, String>();
    static {
        Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(Constant.REQ_INSCRIPTION, URL_INSCRIPTION);
        aMap.put(Constant.REQ_DESINSCRIPTION, URL_DESINSCRIPTION);
        aMap.put(Constant.REQ_LOG, URL_LOG);
        aMap.put(Constant.REQ_GROUP_USERS, URL_GROUP_USERS);
        aMap.put(Constant.REQ_GROUP_CREATE, URL_GROUP_CREATE);
        aMap.put(Constant.REQ_GROUP_DELETE, URL_GROUP_DELETE);
        aMap.put(Constant.REQ_GROUP_INVITE_USER, URL_GROUP_INVITE_USER);
        aMap.put(Constant.REQ_GROUP_QUIT, URL_GROUP_QUIT);
        aMap.put(Constant.REQ_GROUP_RESP_INVITE, URL_GROUP_INVITE_USER);
        aMap.put(Constant.REQ_GROUP_UPDATE, URL_GROUP_UPDATE);
        aMap.put(Constant.REQ_GROUPS, URL_GROUPS);
        aMap.put(Constant.REQ_GROUP_USERS, URL_GROUP_USERS);
        aMap.put(Constant.REQ_NOTE_UPDATE, URL_NOTE_CHECK);
        aMap.put(Constant.REQ_NOTE_CREATE, URL_NOTE_CREATE);
        aMap.put(Constant.REQ_NOTE_DELETE, URL_NOTE_DELETE);
        aMap.put(Constant.REQ_NOTES, URL_NOTES);
        aMap.put(Constant.REQ_LIST_CREATE, URL_LIST_CREATE);
        aMap.put(Constant.REQ_LIST_DELETE, URL_LIST_DELETE);
        aMap.put(Constant.REQ_LIST_ITEM_ADD, URL_LIST_ITEM_CREATE);
        aMap.put(Constant.REQ_LIST_ITEM_DELETE, URL_LIST_ITEM_DELETE);
        aMap.put(Constant.REQ_LIST_ITEMS, URL_LIST_ITEMS);
        aMap.put(Constant.REQ_LIST_UPDATE, URL_LIST_UPDATE);
        aMap.put(Constant.REQ_LISTS, URL_LISTS);
        aMap.put(Constant.REQ_ITEM_CHECK, URL_LIST_ITEM_CHECK);
        aMap.put(Constant.REQ_BILL_CREATE, URL_BILL_CREATE);
        aMap.put(Constant.REQ_BILL_DASHBOARD, URL_BILL_DASHBOARD);
        aMap.put(Constant.REQ_BILL_DELETE, URL_BILL_DELETE);
        aMap.put(Constant.REQ_BILL_PAID, URL_BILL_PAID);
        aMap.put(Constant.REQ_BILL_USER, URL_BILLS_USER);
        aMap.put(Constant.REQ_BILLS, URL_BILLS);
        aMap.put(Constant.REQ_REGISTER_GCM, URL_CDM_REGISTER);
        aMap.put(Constant.REQ_GROUP_NOTIFICATION, URL_GROUP_PUSH);
        UrlMap = Collections.unmodifiableMap(aMap);
    }
}
