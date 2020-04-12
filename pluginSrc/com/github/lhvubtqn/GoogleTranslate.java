package com.github.lhvubtqn;

import java.util.HashMap;
import java.util.Map;

import main.IDictionary;

public class GoogleTranslate extends IDictionary {
	Map<String, String> dict;
	
	public GoogleTranslate() {
		dict = new HashMap<>();
		dict.put("0", "zero");
		dict.put("frustrated", "the feeling you feel when you can't accomplish the thing you longe for");
	}

	@Override
	public String getName() {
		return "Google Translate";
	}

	@Override
	public String lookUp(String word) {
		String permissionTest = System.getProperty("user.home");
		return dict.get(word);
	}

}
