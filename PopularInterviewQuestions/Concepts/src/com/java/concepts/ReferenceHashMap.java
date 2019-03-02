package com.java.concepts;

import java.util.IdentityHashMap;

public class ReferenceHashMap {

	public static void main(String[] args) {
		Integer[] nums = {1,2,3};
		Integer[] nums2 = nums;
		// Similar to hashmap but the keys are compared by reference instead of value.
 		IdentityHashMap<Integer[], Integer> referenceMap = new IdentityHashMap<>();
		referenceMap.put(nums, 1);
		if(referenceMap.containsKey(nums2)){
			System.out.println("keys are compared by reference");
		}
		
		
		// A JVM optimization.
		String s1 = "abc";
		String s2 = "abc";
		IdentityHashMap<String, Integer> referenceStringMap = new IdentityHashMap<>();
		referenceStringMap.put(s1, 1);
		referenceStringMap.put(s2, 2);
		
		System.out.println(referenceStringMap.size());

	}
	
	

}
