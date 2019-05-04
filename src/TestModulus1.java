import java.util.Date;

public class TestModulus1 {
	public static void main(String[] args) {
		long time1, time2;
		time1 = new Date().getTime();
		
		int num = 897876455;
		int index = 234985459;
		int currNum = 0;
		/* 10 million times */
		for(int i=0; i<1E7; i++) {
			currNum = num % index;
		}
		
		time2 = new Date().getTime();
		
		System.out.print("Time taken to calcualte modulus of even number ");
		System.out.println("is " + (time2 - time1) + "ms");
		
		
		
		
		long time3, time4;
		time3 = new Date().getTime();
		
		num = powerOfTwo(897876455);
		index = powerOfTwo(234985459);
		currNum = 0;
		/* 10 million times */
		for(int i=0; i<1E7; i++) {
			currNum = num * index >> 32;
		}
		
		time4 = new Date().getTime();
		
		System.out.print("Time taken to calcualte modulus of power of 2 ");
		System.out.println("is " + (time4 - time3) + "ms");
		
	}
	
	public static int powerOfTwo(int _size) {
		int n = 2;
		while (n < _size) {
			n *= 2;
		}
		return n;
	}
}
