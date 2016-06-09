package random;

public class Random {

	public static void main(String[] args) {
		int i=0;
		while (i<50) {
			double a = 1 + Math.random();
			double l = a * 1000000000;
			String str = String.valueOf((int) l);
			System.out.println(str);
			System.out.println();
			i++;
		}
		
	}
}