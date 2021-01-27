/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2021, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ----------------
 * FlowDataset.java
 * ----------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.data.flow;

import java.util.List;
import org.jfree.data.general.Dataset;

/**
 * A dataset representing flows from a set of sources to a set of destinations.
 * 
 * @param <K> the type for the keys used to identify sources and destinations 
 *     ({@code String} is a good default choice).
 */
public interface FlowDataset<K extends Comparable<K>> extends Dataset {
    
    /**
     * Returns the number of flow stages.  
     * 
     * @return The number of flow stages.
     */
    int getStageCount();
    
    /**
     * Returns a list of the sources at the specified stage.
     * 
     * @param stage  the stage index.
     * 
     * @return A list of the sources at the specified stage.
     */
    List<K> getSources(int stage);
    
    /**
     * Returns a list of the destinations at the specified stage.
     * 
     * @param stage  the stage index.
     * 
     * @return 
     */
    List<K> getDestinations(int stage);
    
    /**
     * Returns the flow between a source node and a destination node at a
     * specified stage.
     * 
     * @param stage
     * @param source
     * @param destination
     * @return 
     */
    Number getFlow(int stage, K source, K destination);
    
}
