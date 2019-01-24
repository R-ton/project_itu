package exception;
public class AvaliderException extends Exception{
	String str;
	public AvaliderException(){
		System.out.println("Date format invalid");
	}
	public AvaliderException(String s){
		this.str=s;
		System.out.println(str);
	}
}