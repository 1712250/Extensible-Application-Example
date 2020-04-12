package main;

import java.security.Policy;

public class Main {

	public static void main(String[] args) {
		// Only internal plugins can have java.security.AllPermission
		// Google Translate plugin tries to invoke System.getProperty("user.home"),
		// ... which will throw an exception
		// Remove 2 lines below will not lead to an exception
//		Policy.setPolicy(new PluginPolicy());
//		System.setSecurityManager(new SecurityManager());
		
		DictionaryProvider provider = DictionaryProvider.getInstance();
		System.out.println(provider.lookUp("1"));
		System.out.println(provider.lookUp("4"));
		System.out.println(provider.lookUp("7"));
		System.out.println(provider.lookUp("frustrated"));
	}
}
