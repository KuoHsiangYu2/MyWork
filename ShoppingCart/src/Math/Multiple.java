package Math;

public class Multiple {
  private int target;
  private int price;
  
  public Multiple(int num, int price) {
	  this.target=num;
	  this.price=price;
  }

public String toString() {
	return "金額:"+(target*price);
}
}  