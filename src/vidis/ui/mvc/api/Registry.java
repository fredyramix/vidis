/*	VIDIS is a simulation and visualisation framework for distributed systems.
	Copyright (C) 2009 Dominik Psenner, Christoph Caks
	This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
	This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>. */
package vidis.ui.mvc.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Registry {
	private static Logger logger = Logger.getLogger(Registry.class);

	private static Map<String, Object> items = new HashMap<String, Object>();
	
	public static void register( String id, Object value) {
		items.put(id, value);
	}
	
	public static void unregister( String id ) {
		items.remove(id);
	}
	
	public Object get( String id ) {
		return items.get(id);
	}
}
