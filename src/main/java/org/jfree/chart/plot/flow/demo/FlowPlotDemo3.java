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
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.flow.FlowColors;
import org.jfree.chart.plot.flow.FlowEntity;
import org.jfree.chart.plot.flow.FlowPlot;
import org.jfree.chart.plot.flow.NodeEntity;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.flow.DefaultFlowDataset;
import org.jfree.data.flow.FlowDataset;

/**
 * A flow plot showing migration data from https://www.data-to-viz.com/graph/sankey.html
 */
public class FlowPlotDemo3 extends JFrame implements ChartMouseListener {
    
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
        FlowDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.addChartMouseListener(this);
        return panel;
    }

    /**
     * Creates a dataset (source https://raw.githubusercontent.com/holtzy/data_to_viz/master/Example_dataset/13_AdjacencyDirectedWeighted.csv).
     *
     * @return a dataset.
     */
    private static FlowDataset createDataset() {
        DefaultFlowDataset<String> dataset = new DefaultFlowDataset<>();
        dataset.setFlow(0, "Africa", "Africa", 3.142471);
        dataset.setFlow(0, "Africa", "Europe", 2.107883);
        dataset.setFlow(0, "Africa", "North America", 0.540887);
        dataset.setFlow(0, "Africa", "West Asia", 0.673004);
        dataset.setFlow(0, "East Asia", "East Asia", 1.630997);
        dataset.setFlow(0, "East Asia", "Europe", 0.601265);
        dataset.setFlow(0, "East Asia", "North America", 0.97306);
        dataset.setFlow(0, "East Asia", "Oceania", 0.333608);
        dataset.setFlow(0, "East Asia", "South East Asia", 0.380388);
        dataset.setFlow(0, "East Asia", "West Asia", 0.869311);
        dataset.setFlow(0, "Europe", "Europe", 2.401476);
        dataset.setFlow(0, "Latin America", "Europe", 1.762587);
        dataset.setFlow(0, "Latin America", "Latin America", 0.879198);
        dataset.setFlow(0, "Latin America", "North America", 3.627847);
        dataset.setFlow(0, "North America", "Europe", 1.215929);
        dataset.setFlow(0, "North America", "North America", 0.276908);
        dataset.setFlow(0, "Oceania", "Europe", 0.17037);
        dataset.setFlow(0, "Oceania", "Oceania", 0.190706);
        dataset.setFlow(0, "South East Asia", "East Asia", 0.525881);
        dataset.setFlow(0, "South Asia", "Europe", 1.390272);
        dataset.setFlow(0, "South Asia", "North America", 1.508008);
        dataset.setFlow(0, "South Asia", "Oceania", 0.34742);
        dataset.setFlow(0, "South Asia", "South Asia", 1.307907);
        dataset.setFlow(0, "South Asia", "West Asia", 4.902081);
        dataset.setFlow(0, "South East Asia", "East Asia", 0.145264);
        dataset.setFlow(0, "South East Asia", "Europe", 0.468762);
        dataset.setFlow(0, "South East Asia", "North America", 1.057904);
        dataset.setFlow(0, "South East Asia", "Oceania", 0.278746);
        dataset.setFlow(0, "South East Asia", "South East Asia", 0.781316);
        dataset.setFlow(0, "Soviet Union", "Europe", 0.60923);
        dataset.setFlow(0, "Soviet Union", "Soviet Union", 1.870501);
        dataset.setFlow(0, "West Asia", "Europe", 0.449623);
        dataset.setFlow(0, "West Asia", "North America", 0.169274);
        dataset.setFlow(0, "West Asia", "West Asia", 0.927243);
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
        JFreeChart chart = new JFreeChart("Migration Patterns", plot);
        return chart;
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

    @Override
    public void chartMouseClicked(ChartMouseEvent event) {
        ChartEntity entity = event.getEntity();
        if (entity instanceof NodeEntity) {
            NodeEntity nodeEntity = (NodeEntity) entity;
            System.out.println("User clicked on node " + nodeEntity.getKey());
        }
        if (entity instanceof FlowEntity) {
            FlowEntity flowEntity = (FlowEntity) entity;
            System.out.println("User clicked on flow " + flowEntity.getKey());
        }
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        
    }

}
