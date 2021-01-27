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
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.general.AbstractDataset;

/**
 * A dataset representing flows between source and destination nodes.
 * @param <K>
 */
public class DefaultFlowDataset<K extends Comparable<K>> extends AbstractDataset 
        implements FlowDataset<K>, PublicCloneable, Serializable {

    /** The nodes from source to destination. */
    private List<List<K>> nodes;
    
    /** Storage for the flows. */
    private Map<FlowKey<K>, Number> flows;
    
    public DefaultFlowDataset() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new ArrayList<>());
        this.nodes.add(new ArrayList<>());
        this.flows = new HashMap<>();
    }
    
    @Override
    public List<K> getSources(int stage) {
        return new ArrayList<>(this.nodes.get(stage));
    }

    @Override
    public List<K> getDestinations(int stage) {
        return new ArrayList<>(this.nodes.get(stage + 1));
    }

    @Override
    public Number getFlow(int stage, K source, K destination) {
        return this.flows.get(new FlowKey<>(stage, source, destination));
    }
    
    public void setFlow(int stage, K source, K destination, double flow) {
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
    }

    @Override
    public int getStageCount() {
        return this.nodes.size() - 1;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultFlowDataset<?> other = (DefaultFlowDataset<?>) obj;
        if (!Objects.equals(this.nodes, other.nodes)) {
            return false;
        }
        if (!Objects.equals(this.flows, other.flows)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nodes);
        hash = 89 * hash + Objects.hashCode(this.flows);
        return hash;
    }
    
    
}
