package com.accpeted.submissions;

import java.util.HashMap;

public class RomanToInteger {

	public static void main(String[] args) {
		// Ran on leetcode.

	}
	
	// Can be asked. Straight forward though.
	public int romanToInt(String s) {
        if(s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum=0;
        for(int i=0;i<s.length()-1;i++) {
            if(map.getOrDefault(s.charAt(i),0) < map.getOrDefault(s.charAt(i+1),0)) sum -= map.getOrDefault(s.charAt(i),0);
            else {
                sum += map.getOrDefault(s.charAt(i),0);
            }
        }
        return sum+map.getOrDefault(s.charAt(s.length()-1),0);
    }

}

abstract class Exam {
String name = "";
String id ="";
public Exam(String name, String id){
	this.name = name;
	this.id = id;
}
}


class E1 extends Exam {
	E1(String name, String id){
		super(name, id);
	}
}
