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

public class FlowPlotDemo1 extends JFrame {
    
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public FlowPlotDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(360, 500));
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
        dataset.setFlow(0, "A", "X", 5.0);
        dataset.setFlow(0, "A", "Y", 2.0);
        dataset.setFlow(0, "A", "Z", 1.0);
        dataset.setFlow(0, "B", "X", 2.0);
        dataset.setFlow(0, "B", "Y", 2.0);
        dataset.setFlow(0, "B", "Z", 2.0);
        
        //dataset.setFlow(1, "W", "D", 6);
        dataset.setFlow(1, "X", "D", 3);
        dataset.setFlow(1, "X", "E", 4);
        dataset.setFlow(1, "Y", "D", 5);
        dataset.setFlow(1, "Y", "E", 3);
        dataset.setFlow(1, "Z", "D", 2);
        dataset.setFlow(1, "Z", "E", 1);

        dataset.setFlow(2, "D", "H", 3);
        dataset.setFlow(2, "D", "I", 3);
        dataset.setFlow(2, "D", "J", 0);
        dataset.setFlow(2, "E", "H", 3);
        dataset.setFlow(2, "E", "I", 2);
        dataset.setFlow(2, "E", "J", 3);
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
       
        plot.setNodeFillColor(new NodeKey(0, "A"), colors[0]);
        plot.setNodeFillColor(new NodeKey(0, "B"), colors[1]);
        plot.setNodeFillColor(new NodeKey(1, "X"), colors[9]);
        plot.setNodeFillColor(new NodeKey(1, "Y"), colors[3]);
        plot.setNodeFillColor(new NodeKey(1, "Z"), colors[4]);
        plot.setNodeFillColor(new NodeKey(2, "D"), colors[5]);
        plot.setNodeFillColor(new NodeKey(2, "E"), colors[7]);
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
        JFreeChart chart = createFlowPlot("Flow Plot", dataset);
        return chart;
    }    
    
    /**
     * Starting point for the demonstration application when it is run as
     * a stand-alone application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        FlowPlotDemo1 demo = new FlowPlotDemo1("JFreeChart: FlowPlotDemo.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
