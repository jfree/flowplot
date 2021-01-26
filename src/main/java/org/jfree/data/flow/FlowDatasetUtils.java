package org.jfree.data.flow;

import java.util.List;

public class FlowDatasetUtils {
    
    public static <K extends Comparable<K>> double calculateInflow(FlowDataset<K> dataset, K destination, int stage) {
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
}

