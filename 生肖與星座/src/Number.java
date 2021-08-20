import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
public class Number {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] x = {"鼠","牛","虎","兔","龍","蛇","馬","羊","猴","雞","狗","豬"};
		String[] y = {"白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","人马座","摩羯座","宝瓶座","双鱼座"};
		
		int a;
		int b;
		int count1=0;
		int count2=0;
		int count3=0;
		for(int i=0 ; i<1000 ; i++) {
		  a=(int)(Math.random()*12);
		  b=(int)(Math.random()*12);
		  
		  if(a==0 && b==0) {
			  
			  count1=count1+1; 
		  }
		}
		System.out.println("(鼠 白羊座)="+count1);
		
		for(int i=0 ; i<1000 ; i++) {
			  a=(int)(Math.random()*12);
			  b=(int)(Math.random()*12);
			  
			  if(a==1 && b==1) {
				  
				  count2=count2+1; 
			  }
			}
			System.out.println("(牛 金牛座)="+count2);
			
			for(int i=0 ; i<1000 ; i++) {
				  a=(int)(Math.random()*12);
				  b=(int)(Math.random()*12);
				  
				  if(a==2 && b==2) {
					  
					  count3=count3+1; 
				  }
				}
				System.out.println("(虎 雙子座)="+count3);
		        System.out.println("排序後");		        
		    		    
		    Map<String,Integer> map= new HashMap<>();
		    		
		    map.put("(鼠 白羊座)",count1);
		    map.put("(牛 金牛座)",count2);
		    map.put("(虎 雙子座)",count3);
		    List<Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
		    list.sort(Entry.comparingByValue());
		    list.forEach(System.out::println);
	}
	}


