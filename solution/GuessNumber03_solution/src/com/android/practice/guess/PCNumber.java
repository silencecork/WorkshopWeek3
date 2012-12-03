package com.android.practice.guess;

import java.util.Random;

import android.util.Log;

public class PCNumber {

	private static final String TAG = "PCNumber";
	private static int[] num = new int[4];
	
	public static void getRandomNumber() {
		Random random = new Random();
		int array_idx = 0;
	    
	    do {
	        boolean isDuplicat = false;
	    	int d = random.nextInt(10);
	        
	        for (int i = 0; i < num.length; i++) {
	              if(d == num[i]) {
	            	  isDuplicat = true;
	                  break; 
	              }
	        }
	        
	        if (!isDuplicat) {
	               num[array_idx++] = d;
	        } 
	    } while(array_idx < num.length);
	    
	    String finalNum = "";
	    for (int i = 0; i < num.length; i++) {
	    	finalNum += num[i];
	    }
	    
		Log.i(TAG, "random number is " + finalNum);
	}
	
	public static String getGuessResult(String num1, String num2, String num3, String num4) {
		int a = 0, b = 0;
		int n1 = 0, n2 = 0, n3 = 0, n4 = 0;
		try {
			n1 = Integer.parseInt(num1);
		} catch (Exception e) { }
		
		try {
			n2 = Integer.parseInt(num2);
		} catch (Exception e) { }
		
		try {
			n3 = Integer.parseInt(num3);
		} catch (Exception e) { }
		
		try {
			n4 = Integer.parseInt(num4);
		} catch (Exception e) { }
		
		int ary2[] = {n1, n2, n3, n4};
		for(int i = 0; i < num.length; i++) {
			if(num[i] == ary2[i]) { 
				a++;
			} else { 
				for(int j = 0; j < 4; j++) {
					if(num[i] == ary2[j]) {
						b++;
						break;
					}
				}
			}
	    }
		return a + "A" + b + "B";
	}
	
}
