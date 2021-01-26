package org.jfree.data.flow;

import java.io.Serializable;
import java.util.Objects;
import org.jfree.chart.util.PublicCloneable;

/**
 * A key that identifies a node in a {@link FlowDataset}.
 * 
 * @param <K> the type for the keys used to identify sources and destinations 
 *     ({@code String} is a good default choice).
 */
public class NodeKey <K extends Comparable<K>> implements PublicCloneable, Serializable {

    /** The stage. */
    private int stage;
    
    /* The source node. */
    private K node;
    
    public NodeKey(int stage, K node) {
        this.stage = stage;
        this.node = node;
    }

    public int getStage() {
        return stage;
    }

    public K getNode() {
        return node;
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
        final NodeKey<?> other = (NodeKey<?>) obj;
        if (this.stage != other.stage) {
            return false;
        }
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.stage;
        hash = 53 * hash + Objects.hashCode(this.node);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}