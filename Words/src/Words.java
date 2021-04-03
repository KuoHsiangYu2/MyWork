
public class Words {

	public static void main(String[] args) {
		String a = "這是一個測試字串";
		String[] words = a.split("");
		
		for(String word : words) {
			System.out.println(word);
		}
		System.out.println(words[2]);
		System.out.println(words[5]);
	}

}
