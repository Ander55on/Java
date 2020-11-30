package map;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
		
	  /*int num1 = -536;
		int num2 = 393;
		int num3 = 387;
		int num4 = -238;
		int num5 = 274;
		int num6 = -720;
		int num7 = 812;
		int num8 = 926;
		int num9 = 992;
		int num10 = 306;
		int num11 = 720;
		
		map.put(num1, num1);
		map.put(num2, num2);
		map.put(num3, num3);
		map.put(num4, num4);
		map.put(num5, num5);
		map.put(num6, num6);
		map.put(num7, num7);
		map.put(num8, num8);
		map.put(num9, num9);
		map.put(num10, num10); 
		map.put(num11, num11); */
		
		for(int i = 0; i < 1000; i++) {
			int num = random.nextInt(10000);
			if(random.nextDouble() < 0.3) {
				num *= -1;
			}
			map.put(num, num);
		}
		
		map.show();
		
	}

}
