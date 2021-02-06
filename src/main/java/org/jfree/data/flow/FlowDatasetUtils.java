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
 * ---------------------
 * FlowDatasetUtils.java
 * ---------------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.data.flow;

import java.util.List;
import org.jfree.graphics2d.Args;

/**
 * Utility methods related to {@link FlowDataset}.
 */
public class FlowDatasetUtils {
    
    /**
     * Returns the total inflow for the specified destination node.
     * 
     * @param <K>
     * @param dataset  the dataset ({@code null} not permitted).
     * @param destination  the destination node ({@code null} not permitted).
     * @param stage  the stage.
     * 
     * @return The total inflow volume. 
     */
    public static <K extends Comparable<K>> double calculateInflow(FlowDataset<K> dataset, K destination, int stage) {
        Args.nullNotPermitted(dataset, "dataset");
        if (stage == 0) {
            return 0.0;  // there are no inflows for stage 0
        }
        double inflow = 0.0;
        List<K> sourceKeys = dataset.getSources(stage - 1);
        for (K key : sourceKeys) {
            Number n = dataset.getFlow(stage - 1, key, destination);
            if (n != null) {
                inflow = inflow + n.doubleValue();
            }
        }
        return inflow;
    }

    /**
     * Returns the total outflow for the specified source node.
     * 
     * @param <K>
     * @param dataset  the dataset ({@code null} not permitted).
     * @param source  the source node ({@code null} not permitted).
     * @param stage  the stage.
     * 
     * @return The total outflow volume.
     */
    public static <K extends Comparable<K>> double calculateOutflow(FlowDataset<K> dataset, K source, int stage) {
        if (stage >= dataset.getStageCount()) {
            return 0.0;  // there are no outflows for the last stage
        }
        double outflow = 0.0;
        List<K> destinationKeys = dataset.getDestinations(stage);
        for (K key : destinationKeys) {
            Number n = dataset.getFlow(stage, source, key);
            if (n != null) {
                outflow = outflow + n.doubleValue();
            }
        }
        return outflow;
    }

    /**
     * Returns the total flow from all sources to all destinations at the 
     * specified stage.
     * 
     * @param dataset  the dataset ({@code null} not permitted).
     * @param stage  the stage.
     * 
     * @return The total flow.
     */
    public static double calculateTotalFlow(FlowDataset dataset, int stage) {
        double total = 0.0;
        for (Object source : dataset.getSources(stage)) {
            for (Object destination : dataset.getDestinations(stage)) {
                Number flow = dataset.getFlow(stage, (Comparable) source, (Comparable) destination);
                if (flow != null) {
                    total = total + flow.doubleValue();
                }
            }
        }
        return total;
    }
    
    /**
     * Returns {@code true} if any of the nodes in the dataset have a property 
     * 'selected' with the value {@code Boolean.TRUE}, and 
     * {@code false} otherwise.
     * 
     * @param dataset  the dataset ({@code null} not permitted).
     * 
     * @return A boolean. 
     */
    public static boolean hasNodeSelections(FlowDataset dataset) {
        for (int s = 0; s < dataset.getStageCount() + 1; s++) { // '+1' to include final destination nodes 
            for (Object source : dataset.getSources(s)) {
                Comparable c = (Comparable) source;
                NodeKey nodeKey = new NodeKey(s, c);
                if (Boolean.TRUE.equals(dataset.getNodeProperty(nodeKey, "selected"))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returns the number of selected nodes.
     * 
     * @param dataset  the dataset ({@code null} not permitted).
     * 
     * @return The number of selected nodes. 
     */
    public static int selectedNodeCount(FlowDataset dataset) {
        int result = 0;
        for (int s = 0; s < dataset.getStageCount() + 1; s++) { // '+1' to include final destination nodes 
            for (Object source : dataset.getSources(s)) {
                Comparable c = (Comparable) source;
                NodeKey nodeKey = new NodeKey(s, c);
                if (Boolean.TRUE.equals(dataset.getNodeProperty(nodeKey, "selected"))) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Returns {@code true} if any of the flows in the dataset have a property 
     * 'selected' with the value {@code Boolean.TRUE}, and 
     * {@code false} otherwise.
     * 
     * @param dataset  the dataset ({@code null} not permitted).
     * 
     * @return A boolean. 
     */
    public static boolean hasFlowSelections(FlowDataset dataset) {
        for (int s = 0; s < dataset.getStageCount(); s++) { 
            for (Object source : dataset.getSources(s)) {
                Comparable sourceKey = (Comparable) source;
                for (Object destination : dataset.getDestinations(s)) {
                    Comparable destinationKey = (Comparable) destination;
                    FlowKey flowKey = new FlowKey(s, sourceKey, destinationKey);
                    if (Boolean.TRUE.equals(dataset.getFlowProperty(flowKey, "selected"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

