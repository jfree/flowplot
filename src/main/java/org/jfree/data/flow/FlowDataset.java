package org.jfree.data.flow;

import java.util.List;

/**
 * A dataset representing flows from a set of sources to a set of destinations.
 * 
 * @param <K> the type for the keys used to identify sources and destinations 
 *     ({@code String} is a good default choice).
 */
public interface FlowDataset<K extends Comparable<K>> {
    
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
