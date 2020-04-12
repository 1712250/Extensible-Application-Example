package dictionaries;

import java.util.HashMap;
import java.util.Map;

import main.IDictionary;

public class LongmanDictionary extends IDictionary {

	Map<String, String> dict;
	
	public LongmanDictionary() {
		dict = new HashMap<>();
		dict.put("4", "four");
		dict.put("5", "five");
		dict.put("6", "six");
	}
	
	@Override
	public String getName() {
		return "Longman Dictionary";
	}

	@Override
	public String lookUp(String word) {
		String permissionTest = System.getProperty("user.home");
		return dict.get(word);
	}

}
