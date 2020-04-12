# Extensible Application Example
> An Java Application in which user can add more plugins without altering code.

### Step by step

__1.__ Write an interface for plugins to extend (both internal and external plugins must extend this interface).

__2.__ Write internal plugins.

__3.__ Compile and build the interface to a .jar file (for external use).
```
  javac TheInterface.java
  jar cfv iplugin.jar TheInterface.class
```

__4.__ Write external plugins.

__5.__ Compile and build those plugins to a .jar file.
```
  javac -d . -cp '.:iplugin.jar' path/to/plugins/java/source/*.java
  jar cfv plugins.jar path/to/plugins/java/source/*.class
```

__6.__ Write a Policy for permission management.

__7.__ Write a ClassLoader ultility.


### Here how it goes

For the sake of simplicity, I create a dictionary application, with two internal dictionaries. User can add more dictionaries for personal use to the application by extending the IDictionary class, which has been build to [dictionary.jar](pluginSrc/dictionary.jar) file.

User can build those external dictionaries to .jar file(s), and add to folder [plugins](plugins) through the "import dictionary function" in the app (ofcourse, we don't have now :blush:)

__1.__ Set the policy
``` java
  Policy.setPolicy(new PluginPolicy());
	System.setSecurityManager(new SecurityManager());
```
Only internal plugin can have all permission:
``` java
  public PermissionCollection getPermissions(CodeSource codeSource) {
      Permissions p = new Permissions();
      if (codeSource.getLocation().toString().contains("plugins")) {
        return p;
      }
      p.add(new AllPermission());
      return p;
    }
```

__2.__ Load internal dictionaries
``` java
  dictionaries.add(new DefaultDictionary());
  dictionaries.add(new LongmanDictionary());
```

__3.__ Load external dictionaries (you can read it [here](src/main/DictionaryProvider.java))
``` java
  loadExternalDictionaries("plugins/");
```

__4.__ Translate and see the result. You will see an exception said that access denied. Try comment out the line System.setSecurityManager(new SecurityManager()), no exception occur!

### References
[Java Docs](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html)

[Adding Plugins to a Java Application by Ulf Dittmer](https://javaranch.com/journal/200607/Plugins.html?fbclid=IwAR1KwpYAPD5VlHm7SfkCf8kwZYOIDM6BHEkD2_5grYzlqR1wo1AX4TTVQJM)

[Stackoverflow: How to create a pluginable Java program?](https://stackoverflow.com/questions/25449/how-to-create-a-pluginable-java-program)

[Java plugins implementation by alexiyorlov](https://alexiyorlov.github.io/tutorials/java-plugins.html)

