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
 * ------------------
 * FlowPlotDemo3.java
 * ------------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */
package org.jfree.chart.plot.flow.demo;

import java.awt.Color;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.flow.FlowColors;
import org.jfree.chart.plot.flow.FlowPlot;
import org.jfree.chart.plot.flow.NodeEntity;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.flow.DefaultFlowDataset;
import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.FlowDatasetUtils;
import org.jfree.data.flow.FlowKey;
import org.jfree.data.flow.NodeKey;

/**
 * A flow plot showing data from https://statisticsnz.shinyapps.io/trade_dashboard/
 */
public class FlowPlotDemo3 extends JFrame implements ChartMouseListener {
    
    private DefaultFlowDataset<String> dataset;
    
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public FlowPlotDemo3(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(760, 500));
        setContentPane(chartPanel);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createDemoPanel() {
        this.dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.addChartMouseListener(this);
        return panel;
    }

    /**
     * Creates a dataset (source https://statisticsnz.shinyapps.io/trade_dashboard/).
     *
     * @return a dataset.
     */
    private static DefaultFlowDataset<String> createDataset() {
        DefaultFlowDataset<String> dataset = new DefaultFlowDataset<>();
        dataset.setFlow(0, "Goods", "Australia", 2101);
        dataset.setFlow(0, "Services", "Australia", 714);
        dataset.setFlow(0, "Goods", "China", 3397);
        dataset.setFlow(0, "Services", "China", 391);
        dataset.setFlow(0, "Goods", "USA", 1748);
        dataset.setFlow(0, "Services", "USA", 583);
        dataset.setFlow(0, "Goods", "United Kingdom", 363);
        dataset.setFlow(0, "Services", "United Kingdom", 178);
        dataset.setFlow(1, "Australia", "Cereal preparations", 179);
        dataset.setFlow(1, "Australia", "Machinery", 173);
        dataset.setFlow(1, "Australia", "Beverages", 170);
        dataset.setFlow(1, "Australia", "Dairy", 165);
        dataset.setFlow(1, "Australia", "Misc Food", 131);
        dataset.setFlow(1, "Australia", "Other Goods", 1283);
        dataset.setFlow(1, "Australia", "Travel", 198);
        dataset.setFlow(1, "China", "Dairy", 848);
        dataset.setFlow(1, "China", "Wood", 706);
        dataset.setFlow(1, "China", "Meat", 463);
        dataset.setFlow(1, "China", "Fruit & Nuts", 296);
        dataset.setFlow(1, "China", "Cereal preparations", 214);
        dataset.setFlow(1, "China", "Other Goods", 870);
        
        dataset.setFlow(1, "USA", "Dairy", 95);
        dataset.setFlow(1, "USA", "Meat", 367);
        dataset.setFlow(1, "USA", "Travel", 90);
        dataset.setFlow(1, "USA", "Wood", 83);
        dataset.setFlow(1, "USA", "Beverages", 157);
        return dataset;
    }
    
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(FlowDataset dataset) {
        FlowPlot plot = new FlowPlot(dataset);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDefaultNodeLabelPaint(Color.WHITE);
        plot.setNodeColorSwatch(FlowColors.createPastelColors());
        JFreeChart chart = new JFreeChart("Selected NZ Exports Sept 2020", plot);
        return chart;
    }    
    
    /**
     * Detect when the user has clicked on a node.  If it is unselected, then
     * it is set to selected and all other nodes are unselected.  If it is
     * selected then the behaviour depends on whether or not other nodes are
     * also selected.  If some other nodes are selected, they will be set
     * to unselected and the clicked node will remain selected.  If no other
     * nodes are selected, then all nodes are set to selected.
     * 
     * @param event  the event. 
     */
    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        ChartEntity entity = event.getEntity();
        if (entity instanceof NodeEntity) {
            NodeEntity nodeEntity = (NodeEntity) entity;
            NodeKey<String> clickeNodeKey = nodeEntity.getKey();
            boolean selected = Boolean.TRUE.equals(this.dataset.getNodeProperty(clickeNodeKey, "selected"));
            if (selected) {
                if (FlowDatasetUtils.selectedNodeCount(dataset) > 1) {
                    for (NodeKey<String> nodeKey : (Set<NodeKey<String>>) dataset.getAllNodes()) {
                        this.dataset.setNodeProperty(nodeKey, "selected", false);
                    }
                    this.dataset.setNodeProperty(clickeNodeKey, "selected", true);
                } else {
                    for (NodeKey<String> nodeKey : dataset.getAllNodes()) {
                        this.dataset.setNodeProperty(nodeKey, "selected", true);
                    }        
                }
            } else {
                for (NodeKey<String> nodeKey : dataset.getAllNodes()) {
                    this.dataset.setNodeProperty(nodeKey, "selected", false);
                }
                this.dataset.setNodeProperty(clickeNodeKey, "selected", true);
            }
            for (FlowKey<String> flowKey : this.dataset.getAllFlows()) {
                this.dataset.setFlowProperty(flowKey, "selected", isSelected(flowKey, this.dataset));
            }
        }
    }
    
    private boolean isSelected(FlowKey<String> flowKey, FlowDataset<String> dataset) {
        NodeKey<String> sourceKey = new NodeKey<>(flowKey.getStage(), flowKey.getSource());
        if (Boolean.TRUE.equals(dataset.getNodeProperty(sourceKey, "selected"))) {
            return true;
        }
        NodeKey<String> destinationKey = new NodeKey<>(flowKey.getStage() + 1, flowKey.getDestination());
        if (Boolean.TRUE.equals(dataset.getNodeProperty(destinationKey, "selected"))) {
            return true;
        }
        return false;
    }

    /**
     * As the mouse moves over a flow, it will be highlighted.
     * 
     * @param event  the event.
     */
    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        // do nothing     
    }

    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        FlowPlotDemo3 demo = new FlowPlotDemo3("JFreeChart: FlowPlotDemo3.java");
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
