package practise;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HashMapComparatorDemo implements Comparator<Integer> {
	static TreeMap<Integer, Integer> map = new TreeMap<>();
	static HashMap<Integer, Integer> maph = new HashMap<>();

	public HashMapComparatorDemo(TreeMap<Integer, Integer> reqMap) {
		this.map = reqMap;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		return this.map.get(o2).compareTo(this.map.get(o1));

	}

	public static void main(String[] args) {

		HashMapComparatorDemo hcd = new HashMapComparatorDemo(new TreeMap<Integer, Integer>());
		map.put(2, 10);
		map.put(3, 9);
		map.put(4, 8);
		map.put(1, 4);
		map.put(7, 6);
		map.put(5, 5);
		TreeMap<Integer, Integer> soretedMap = new TreeMap<>(hcd);
		soretedMap.putAll(map);
		for (Entry<Integer, Integer> entry : soretedMap.entrySet()) {
			System.out.println("KEY:" + entry.getKey() + "VALUE:" + entry.getValue());

		}

		maph.put(11, 12);
		maph.put(1, 12);
		maph.put(10, 12);
		TreeMap<Integer, Integer> HsoretedMap = new TreeMap<>();
		HsoretedMap.putAll(maph);
		for (Integer i : HsoretedMap.keySet()) {
			System.out.println(i);
		}

	}

}
