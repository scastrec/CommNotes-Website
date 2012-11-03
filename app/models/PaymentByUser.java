package models;
/**
 * PaymentByUser : represent what the connected user has to paid to a define user 
 */
public class PaymentByUser {
	private String _value;
	private String _idReceiver;
	private String _name;
	private String _idUserPayer;
	
	public PaymentByUser(String pValue, String pIdReceiver, String pNameReceiver, String pIdUserPayer)	{
		_value = pValue;
		_idReceiver = pIdReceiver;
		_name = pNameReceiver;
		_idUserPayer = 	pIdUserPayer;	
	}
	
	public String get_value() {
		return _value;
	}
	public String get_idReceiver() {
		return _idReceiver;
	}
	public String get_name() {
		return _name;
	}
	public String get_idUserPayer() {
		return _idUserPayer;
	}
}
