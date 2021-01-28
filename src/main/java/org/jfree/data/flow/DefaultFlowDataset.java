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
 * -----------------------
 * DefaultFlowDataset.java
 * -----------------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.data.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jfree.chart.plot.flow.FlowUtils;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.general.AbstractDataset;

/**
 * A dataset representing flows between source and destination nodes.
 * 
 * @param <K> the type for the keys used to identify sources and destinations 
 *     (instances should be immutable, {@code String} is a good default choice).
 */
public class DefaultFlowDataset<K extends Comparable<K>> extends AbstractDataset 
        implements FlowDataset<K>, PublicCloneable, Serializable {

    /** The nodes from source to destination. */
    private List<List<K>> nodes;
    
    /** Storage for the flows. */
    private Map<FlowKey<K>, Number> flows;
    
    /**
     * Creates a new dataset that is initially empty.
     */
    public DefaultFlowDataset() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new ArrayList<>());
        this.nodes.add(new ArrayList<>());
        this.flows = new HashMap<>();
    }

    /**
     * Returns a list of the source nodes for the specified stage.
     * 
     * @param stage  the stage (0 to {@code getStageCount() - 1}).
     * 
     * @return A list of source nodes (possibly empty but never {@code null}). 
     */
    @Override
    public List<K> getSources(int stage) {
        return new ArrayList<>(this.nodes.get(stage));
    }

    /**
     * Returns a list of the destination nodes for the specified stage.
     * 
     * @param stage  the stage (0 to {@code getStageCount() - 1}).
     * 
     * @return A list of destination nodes (possibly empty but never {@code null}). 
     */
    @Override
    public List<K> getDestinations(int stage) {
        return new ArrayList<>(this.nodes.get(stage + 1));
    }

    /**
     * Returns the flow between a source node and a destination node at a
     * specified stage.  This must be 0 or greater.  The dataset can return
     * {@code null} to represent an unknown value.
     * 
     * @param stage  the stage index (0 to {@code getStageCount()} - 1).
     * @param source  the source ({@code null} not permitted). 
     * @param destination  the destination ({@code null} not permitted).
     * 
     * @return The flow (zero or greater, possibly {@code null}). 
     */
    @Override
    public Number getFlow(int stage, K source, K destination) {
        return this.flows.get(new FlowKey<>(stage, source, destination));
    }

    /**
     * Sets the flow between a source node and a destination node at the 
     * specified stage.  A new stage will be added if {@code stage} is equal
     * to {@code getStageCount()}.
     * 
     * @param stage  the stage (0 to {@code getStageCount()}.
     * @param source  the source ({@code null} not permitted).
     * @param destination  the destination ({@code null} not permitted).
     * @param flow  the flow (0 or greater).
     */
    public void setFlow(int stage, K source, K destination, double flow) {
        Args.requireInRange(stage, "stage", 0, getStageCount());
        Args.nullNotPermitted(source, "source");
        Args.nullNotPermitted(destination, "destination");
        if (stage > this.nodes.size() - 2) {
            this.nodes.add(new ArrayList<>());
        }
        if (!getSources(stage).contains(source)) {
            this.nodes.get(stage).add(source);
        }
        if (!getDestinations(stage).contains(destination)) {
            this.nodes.get(stage + 1).add(destination);
        }
        this.flows.put(new FlowKey<>(stage, source, destination), flow);
        fireDatasetChanged();
    }

    /**
     * Returns the number of flow stages.  A flow dataset always has one or
     * more stages, so this method will return {@code 1} even for an empty
     * dataset (one with no sources, destinations or flows defined).
     * 
     * @return The number of flow stages.
     */
    @Override
    public int getStageCount() {
        return this.nodes.size() - 1;
    }

    /**
     * Returns a clone of the dataset.
     * 
     * @return A clone of the dataset.
     * 
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        DefaultFlowDataset<K> clone = (DefaultFlowDataset) super.clone();
        clone.flows = new HashMap<>(this.flows);
        clone.nodes = new ArrayList<>();
        for (List<?> list : nodes) {
            clone.nodes.add((List<K>) FlowUtils.cloneList(list));
        }
        return clone;
    }

    /**
     * Tests this dataset for equality with an arbitrary object.  This method
     * will return {@code true} if the object implements the 
     * {@link FlowDataset} and defines the exact same set of nodes and flows 
     * as this dataset.
     * 
     * @param obj  the object to test equality against ({@code null} permitted).
     * 
     * @return A boolean. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlowDataset)) {
            return false;
        }
        final FlowDataset other = (FlowDataset) obj;
        if (other.getStageCount() != getStageCount()) {
            return false;
        }
        for (int stage = 0; stage < getStageCount(); stage++) {
            if (!Objects.equals(other.getSources(stage), getSources(stage))) {
                return false;
            }
            if (!Objects.equals(other.getDestinations(stage), getDestinations(stage))) {
                return false;
            }
            for (K source : getSources(stage)) {
                for (K destination : getDestinations(stage)) {
                    if (!Objects.equals(other.getFlow(stage, source, destination), getFlow(stage, source, destination))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(getSources(0));
        hash = 89 * hash + Objects.hashCode(getDestinations(getStageCount() - 1));
        return hash;
    }

}
