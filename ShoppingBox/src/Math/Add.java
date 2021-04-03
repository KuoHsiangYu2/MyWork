package Math;

public class Add {
	private int a1;
	private int a2;
	private int a3;
	
	  
	  public Add(int a1, int a2, int a3) {
		  this.a1=a1;
		  this.a2=a2;
		  this.a3=a3;
		  
	  }

	public String toString() {
		return "總共金額:"+(a1+a2+a3);
	}
}
