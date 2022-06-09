package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()){
			map.get(itr.next());
			itr.remove();
		}
	}

}
