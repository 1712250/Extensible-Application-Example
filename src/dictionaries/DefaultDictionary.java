package dictionaries;

import java.util.HashMap;
import java.util.Map;

import main.IDictionary;

public class DefaultDictionary extends IDictionary {
	Map<String, String> dict;

	public DefaultDictionary() {
		dict = new HashMap<>();
		dict.put("1", "one");
		dict.put("2", "two");
		dict.put("3", "three");
	}
	
	@Override
	public String getName() {
		return "Default Dictionary";
	}

	@Override
	public String lookUp(String word) {
		String permissionTest = System.getProperty("user.home");
		return dict.get(word);
	}

}
