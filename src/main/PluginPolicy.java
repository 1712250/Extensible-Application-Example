package main;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;

public class PluginPolicy extends Policy {
	@Override
	public PermissionCollection getPermissions(CodeSource codeSource) {
		Permissions p = new Permissions();
		if (codeSource.getLocation().toString().contains("plugins")) {
			return p;
		}
		p.add(new AllPermission());
		return p;
	}
}
