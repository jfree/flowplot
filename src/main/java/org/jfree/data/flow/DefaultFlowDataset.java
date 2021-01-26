package org.jfree.data.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jfree.chart.plot.flow.FlowUtils;
import org.jfree.chart.util.PublicCloneable;

/**
 * A dataset representing flows between source and destination nodes.
 * @param <K>
 */
public class DefaultFlowDataset<K extends Comparable<K>> implements FlowDataset<K>, PublicCloneable, Serializable {

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
