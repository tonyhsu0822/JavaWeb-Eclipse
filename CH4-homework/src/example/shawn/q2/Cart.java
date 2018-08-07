package example.shawn.q2;

public class Cart {
	
	private int total = 0;
	private int javase9;
	private int jquery;
	private int python35;
	
	public Cart() {
		javase9 = 0;
		jquery = 0;
		python35 = 0;
		total = 0;
	}
	
	public void addBook(String bookName) {
		switch(bookName) {
			case "javase9":
				javase9++;
				break;
			case "jquery":
				jquery++;
				break;
			case "python35":
				python35++;
				break;
			default:
				break;
		}
		total = javase9 + jquery + python35;
	}
	
	public int getJavase9() {
		return javase9;
	}
	
	public int getJquery() {
		return jquery;
	}
	
	public int getPython35() {
		return python35;
	}

	public int getTotal() {
		return total;
	}
}
