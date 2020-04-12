package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;

import dictionaries.DefaultDictionary;
import dictionaries.LongmanDictionary;

public class DictionaryProvider {
	private List<IDictionary> dictionaries;

	private static DictionaryProvider instance;

	private DictionaryProvider() {
		dictionaries = new ArrayList<IDictionary>();
		dictionaries.add(new DefaultDictionary());
		dictionaries.add(new LongmanDictionary());
		loadExternalDictionaries("plugins/");
	};

	public static DictionaryProvider getInstance() {
		if (instance == null) {
			synchronized (DictionaryProvider.class) {
				if (instance == null) {
					instance = new DictionaryProvider();
				}
			}
		}
		return instance;
	}

	public String lookUp(String word) {
		for (IDictionary dict : dictionaries) {
			String result = dict.lookUp(word);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	private void loadExternalDictionaries(String pluginsDir) {

		File pluginFolder = new File(pluginsDir);
		if (!pluginFolder.exists()) {
			if (pluginFolder.mkdirs()) {
				System.out.println("Created plugin folder");
			}
		}

		File[] files = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));
		ArrayList<URL> urls = new ArrayList<>();
		ArrayList<String> classes = new ArrayList<>();
		if (files != null) {
			Arrays.stream(files).forEach(file -> {
				try {
					JarFile jarFile = new JarFile(file);
					urls.add(new URL("jar:file:" + pluginsDir + "/" + file.getName() + "!/"));
					jarFile.stream().forEach(jarEntry -> {
						if (jarEntry.getName().endsWith(".class")) {
							classes.add(jarEntry.getName());
						}
					});
					jarFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			try {
				URLClassLoader pluginLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
				classes.forEach(s -> {
					try {
						IDictionary dict = (IDictionary) pluginLoader
								.loadClass(s.replaceAll("/", ".").replace(".class", "")).getConstructor().newInstance();
						dictionaries.add(dict);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				pluginLoader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
