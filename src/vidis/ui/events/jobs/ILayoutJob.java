/*	VIDIS is a simulation and visualisation framework for distributed systems.
	Copyright (C) 2009 Dominik Psenner, Christoph Caks
	This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
	This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>. */
package vidis.ui.events.jobs;

import java.util.Collection;

import vidis.data.sim.SimNode;
import vidis.ui.model.graph.layouts.IGraphLayout;

/**
 * layout job interface
 * @author Dominik
 *
 */
public interface ILayoutJob extends IJob {
	public IGraphLayout getLayout();
	public Collection<SimNode> getNodes();
}