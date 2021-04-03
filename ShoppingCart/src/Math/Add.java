package Math;

public class Add {
	private int a1;
	private int a2;
	private int a3;
	private int a4;
	private int a5;
	  
	  public Add(int a1, int a2, int a3, int a4, int a5) {
		  this.a1=a1;
		  this.a2=a2;
		  this.a3=a3;
		  this.a4=a4;
		  this.a5=a5;
	  }

	public String toString() {
		return "總共金額:"+(a1*100+a2*150+a3*80+a4*120+a5*70);
	}
	}  

