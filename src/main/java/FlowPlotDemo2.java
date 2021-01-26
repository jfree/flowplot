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
 * -----------------
 * FlowPlotDemo.java
 * -----------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.flow.FlowPlot;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.flow.DefaultFlowDataset;
import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.NodeKey;

public class FlowPlotDemo2 extends JFrame {
    
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public FlowPlotDemo2(String title) {
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
    public static JPanel createDemoPanel() {
        FlowDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Creates a dataset.
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
        return dataset;
    }

    public static Color[] createFluoColors() {
        Color[] result = new Color[10]; 
        result[0] = new Color(108, 236, 137);
        result[1] = new Color(253, 187, 46);
        result[2] = new Color(56, 236, 216);
        result[3] = new Color(171, 231, 51);
        result[4] = new Color(221, 214, 74);
        result[5] = new Color(106, 238, 70);
        result[6] = new Color(172, 230, 100);
        result[7] = new Color(242, 191, 82);
        result[8] = new Color(221, 233, 56);
        result[9] = new Color(242, 206, 47);
        return result;
    }
   
    static final JFreeChart createFlowPlot(String title, FlowDataset dataset) {
        FlowPlot plot = new FlowPlot(dataset);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDefaultNodeLabelPaint(Color.WHITE);
        Color[] colors = createFluoColors();
       
        plot.setNodeFillColor(new NodeKey(0, "Africa"), colors[0]);
        plot.setNodeFillColor(new NodeKey(1, "Africa"), colors[0]);
        plot.setNodeFillColor(new NodeKey(0, "East Asia"), colors[1]);
        plot.setNodeFillColor(new NodeKey(1, "East Asia"), colors[1]);
        plot.setNodeFillColor(new NodeKey(0, "Latin America"), colors[2]);
        plot.setNodeFillColor(new NodeKey(1, "Latin America"), colors[2]);
        plot.setNodeFillColor(new NodeKey(0, "Europe"), colors[3]);
        plot.setNodeFillColor(new NodeKey(1, "Europe"), colors[3]);
        plot.setNodeFillColor(new NodeKey(0, "North America"), colors[4]);
        plot.setNodeFillColor(new NodeKey(1, "North America"), colors[4]);
        plot.setNodeFillColor(new NodeKey(0, "Oceania"), colors[5]);
        plot.setNodeFillColor(new NodeKey(1, "Oceania"), colors[5]);
        plot.setNodeFillColor(new NodeKey(0, "South Asia"), colors[6]);
        plot.setNodeFillColor(new NodeKey(1, "South Asia"), colors[6]);
        JFreeChart chart = new JFreeChart(title, plot);
        return chart;
    }
    
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(FlowDataset dataset) {
        JFreeChart chart = createFlowPlot("Migration Patterns", dataset);
        return chart;
    }    
    
    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        FlowPlotDemo2 demo = new FlowPlotDemo2("JFreeChart: FlowPlotDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
