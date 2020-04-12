package com.github.lhvubtqn;

import java.util.HashMap;
import java.util.Map;

import main.IDictionary;

public class CambrigdeDictionary extends IDictionary {
	
	Map<String, String> dict;
	
	public CambrigdeDictionary() {
		dict = new HashMap<>();
		dict.put("7", "seven");
		dict.put("8", "eight");
		dict.put("9", "nine");
	}

	@Override
	public String getName() {
		return "Cambrigde Dictionary";
	}

	@Override
	public String lookUp(String word) {
		String permissionTest = System.getProperty("user.home");
		return dict.get(word);
	}

}
