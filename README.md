# Extensible Application Example
> An Java Application in which user can add more plugins without altering code.

### Step by step
1. Write an interface for plugins to extend (both internal and external plugins must extend this interface).
2. Write internal plugins.
3. Compile and build the interface to a .jar file (for external use).
```
  javac TheInterface.java
  jar cfv iplugin.jar TheInterface.class
```
4. Write external plugins.
5. Compile and build those plugins to a .jar file.
```
  javac -d . -cp '.:iplugin.jar' path/to/plugins/java/source/*.java
  jar cfv plugins.jar path/to/plugins/java/source/*.class
```
6. Write a Policy for permission management.
7. Write a ClassLoader ultility.

### Here how it goes

For the sake of simplicity, I create a dictionary application, with two internal dictionaries. User can add more dictionaries for personal use to the application by extending the IDictionary class, which has been build to [dictionary.jar](pluginSrc/dictionary.jar) file.

User can build those external dictionaries to .jar file(s), and add to folder [plugins](plugins) through the "import dictionary function" in the app (ofcource, we don't have now :blush:)

1. Set the policy
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
2. Load internal dictionaries
``` java
  dictionaries.add(new DefaultDictionary());
  dictionaries.add(new LongmanDictionary());
```
3. Load external dictionaries (you can read it [here](src/main/DictionaryProvider.java))
``` java
  loadExternalDictionaries("plugins/");
```

4. Translate and see the result. You will see an exception said that access denied. Try comment out the line System.setSecurityManager(new SecurityManager()), no exception occur!
