package vidis.sim.classloader.modules.impl;

import java.io.IOException;
import java.io.InputStream;

import vidis.sim.classloader.modules.interfaces.IModuleComponent;

public abstract class AModuleFile implements IModuleComponent {
	public abstract String getName();
	
	public boolean isModule() {
		return false;
	}
	public boolean isModuleFile() {
		return true;
	}

	public abstract InputStream getInputStream() throws IOException;
	
}
