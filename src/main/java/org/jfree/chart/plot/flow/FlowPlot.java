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
 * -------------
 * FlowPlot.java
 * -------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.plot.flow;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.text.TextUtils;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.FlowDatasetUtils;
import org.jfree.data.flow.FlowKey;
import org.jfree.data.flow.NodeKey;

/**
 */
public class FlowPlot extends Plot {

    private FlowDataset dataset;
    
    /** 
     * The percentage of the stage width to assign to a gap between the node
     * and the flow representation. 
     */
    private double flowMargin = 0.005;
    
    private double nodeWidth = 20.0;
    
    /** The gap between nodes (expressed as a percentage of the plot height). */
    private double nodeMargin = 0.01;

    private Map<NodeKey, Color> nodeColorMap;

    private Font defaultNodeLabelFont;
    
    private Paint defaultNodeLabelPaint;
    
    private double nodeLabelOffsetX;
    
    private double nodeLabelOffsetY;
    
    public FlowPlot(FlowDataset dataset) {
        this.dataset = dataset;
        this.nodeColorMap = new HashMap<>();
        this.defaultNodeLabelFont = new Font(Font.DIALOG, Font.BOLD, 12);
        this.defaultNodeLabelPaint = Color.BLACK;
        this.nodeLabelOffsetX = 2.0;
        this.nodeLabelOffsetY = 2.0;
    }
    
    @Override
    public String getPlotType() {
        return "FlowPlot";
    }
    
    public FlowDataset getDataset() {
        return this.dataset;
    }
    
    public void setDataset(FlowDataset dataset) {
        this.dataset = dataset;
        fireChangeEvent();
    }
    

    public double getNodeWidth() {
        return nodeWidth;
    }

    public void setNodeWidth(double nodeWidth) {
        this.nodeWidth = nodeWidth;
        fireChangeEvent();
    }

    public void setNodeFillColor(NodeKey nodeKey, Color color) {
        this.nodeColorMap.put(nodeKey, color);
        fireChangeEvent();
    }
    
    protected Color getNodeColor(NodeKey nodeKey) {
        Color result = this.nodeColorMap.get(nodeKey);
        if (result == null) {
            result = Color.LIGHT_GRAY;
        }
        return result;
    }

    public Font getDefaultNodeLabelFont() {
        return defaultNodeLabelFont;
    }

    public void setDefaultNodeLabelFont(Font defaultNodeLabelFont) {
        this.defaultNodeLabelFont = defaultNodeLabelFont;
        fireChangeEvent();
    }

    public Paint getDefaultNodeLabelPaint() {
        return defaultNodeLabelPaint;
    }

    public void setDefaultNodeLabelPaint(Paint defaultNodeLabelPaint) {
        this.defaultNodeLabelPaint = defaultNodeLabelPaint;
        fireChangeEvent();
    }
    
    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {

        drawBackground(g2, area);
        
        int stageCount = this.dataset.getStageCount();
        
        // we need to ensure there is space to show all the inflows and all 
        // the outflows at each node group, so first we calculate the max
        // flow space required - for each node in the group, consider the 
        // maximum of the inflow and the outflow
        int maxNodes = this.dataset.getSources(0).size();
        double maxFlowSpace = 0.0;
        for (int stage = 0; stage < this.dataset.getStageCount(); stage++) {
            double nodeTotal = 0.0;
            for (Object s : this.dataset.getSources(stage)) {
                maxNodes = Math.max(maxNodes, this.dataset.getDestinations(stage).size());
                Comparable source = (Comparable) s;
                double inflow = FlowDatasetUtils.calculateInflow(this.dataset, source, stage);
                double outflow = FlowDatasetUtils.calculateOutflow(this.dataset, source, stage);
                nodeTotal = nodeTotal + Math.max(inflow, outflow);
            }
            maxFlowSpace = Math.max(maxFlowSpace, nodeTotal);
        }
        
        double stageWidth = (area.getWidth() - ((stageCount + 1) * this.nodeWidth)) / stageCount;
        for (int stage = 0; stage < this.dataset.getStageCount(); stage++) {
            
            double stageLeft = area.getX() + (stage + 1) * this.nodeWidth + (stage * stageWidth);
            double stageRight = stageLeft + stageWidth;
            
            // calculate the source node and flow rectangles
            double margin = this.nodeMargin * area.getHeight();
            double availableHeight = area.getHeight() - ((maxNodes-1) * margin);
            Map<Comparable, Rectangle2D> sourceNodeRects = new HashMap<>();
            Map<FlowKey, Rectangle2D> sourceFlowRects = new HashMap<>();
            double nodeY = area.getY();
            for (Object s : this.dataset.getSources(stage)) {
                Comparable source = (Comparable) s;
                double inflow = FlowDatasetUtils.calculateInflow(dataset, source, stage);
                double outflow = FlowDatasetUtils.calculateOutflow(dataset, source, stage);
                double nodeHeight = (Math.max(inflow, outflow) / maxFlowSpace) * availableHeight;
                sourceNodeRects.put(source, new Rectangle2D.Double(stageLeft - nodeWidth, nodeY, nodeWidth, nodeHeight));
                double y = nodeY;
                for (Object d : this.dataset.getDestinations(stage)) {
                    Comparable destination = (Comparable) d;
                    Number flow = this.dataset.getFlow(stage, source, destination);
                    if (flow != null) {
                        double height = availableHeight * (flow.doubleValue() / maxFlowSpace);
                        Rectangle2D rect = new Rectangle2D.Double(stageLeft - nodeWidth, y, nodeWidth, height);
                        sourceFlowRects.put(new FlowKey(stage, source, destination), rect);
                        y = y + height;
                    }
                }
                nodeY = nodeY + nodeHeight + margin;
            }
            
            // calculate the destination rectangles
            Map<Comparable, Rectangle2D> destNodeRects = new HashMap<>();
            Map<FlowKey, Rectangle2D> destFlowRects = new HashMap<>();
            nodeY = area.getY();
            for (Object d : this.dataset.getDestinations(stage)) {
                Comparable destination = (Comparable) d;
                double inflow = FlowDatasetUtils.calculateInflow(dataset, destination, stage + 1);
                double outflow = FlowDatasetUtils.calculateOutflow(dataset, destination, stage + 1);
                double nodeHeight = (Math.max(inflow, outflow) / maxFlowSpace) * availableHeight;
                destNodeRects.put(destination, new Rectangle2D.Double(stageRight, nodeY, nodeWidth, nodeHeight));
                double y = nodeY;
                for (Object s : this.dataset.getSources(stage)) {
                    Comparable source = (Comparable) s;
                    Number flow = this.dataset.getFlow(stage, source, destination);
                    if (flow != null) {
                        double height = (flow.doubleValue() / maxFlowSpace) * availableHeight;
                        Rectangle2D rect = new Rectangle2D.Double(stageRight, y, nodeWidth, height);
                        y = y + height;
                        destFlowRects.put(new FlowKey(stage, source, destination), rect);
                    }
                }
                nodeY = nodeY + nodeHeight + margin;
            }
        
            double flowOffset = area.getWidth() * this.flowMargin;
            for (Object s : this.dataset.getSources(stage)) {
                Comparable source = (Comparable) s;
                NodeKey nodeKey = new NodeKey(stage, source);
                Rectangle2D nodeRect = sourceNodeRects.get(source);
                g2.setPaint(getNodeColor(nodeKey));
                g2.fill(nodeRect);
                                
                for (Object d : this.dataset.getDestinations(stage)) {
                    Comparable destination = (Comparable) d;
                    FlowKey flowKey = new FlowKey(stage, source, destination);
                    Rectangle2D sourceRect = sourceFlowRects.get(flowKey);
                    if (sourceRect == null) { 
                        continue; 
                    }
                    Rectangle2D destRect = destFlowRects.get(flowKey);
                
                    Path2D connect = new Path2D.Double();
                    connect.moveTo(sourceRect.getMaxX() + flowOffset, sourceRect.getMinY());
                    connect.curveTo(stageLeft + stageWidth / 2.0, sourceRect.getMinY(), stageLeft + stageWidth / 2.0, destRect.getMinY(), destRect.getX() - flowOffset, destRect.getMinY());
                    connect.lineTo(destRect.getX() - flowOffset, destRect.getMaxY());
                    connect.curveTo(stageLeft + stageWidth / 2.0, destRect.getMaxY(), stageLeft + stageWidth / 2.0, sourceRect.getMaxY(), sourceRect.getMaxX() + flowOffset, sourceRect.getMaxY());
                    connect.closePath();
                    Color nc = getNodeColor(nodeKey); 
                    GradientPaint gp = new GradientPaint((float) sourceRect.getMaxX(), 0, nc, (float) destRect.getMinX(), 0, new Color(nc.getRed(), nc.getGreen(), nc.getBlue(), 128));
                    g2.setPaint(gp);
                    g2.fill(connect);

                }
                
                g2.setFont(this.defaultNodeLabelFont);
                g2.setPaint(this.defaultNodeLabelPaint);
                TextUtils.drawAlignedString(source.toString(), g2, (float) (nodeRect.getMaxX() + flowOffset + this.nodeLabelOffsetX), (float) (nodeRect.getY() + this.nodeLabelOffsetY), TextAnchor.TOP_LEFT);

                // when rendering the final stage, draw the destination nodes
                if (stage == this.dataset.getStageCount() - 1) {
                    for (Object d : this.dataset.getDestinations(stage)) {
                        Comparable destination = (Comparable) d;
                        nodeKey = new NodeKey(stage, destination);
                        nodeRect = destNodeRects.get(destination);
                        g2.setPaint(getNodeColor(nodeKey));
                        g2.fill(nodeRect);

                        g2.setFont(this.defaultNodeLabelFont);
                        g2.setPaint(this.defaultNodeLabelPaint);
                        TextUtils.drawAlignedString(destination.toString(), g2, (float) (nodeRect.getX() - flowOffset - this.nodeLabelOffsetX), (float) (nodeRect.getY() + this.nodeLabelOffsetY), TextAnchor.TOP_RIGHT);

                    }
                }
            }
        }
    }
    
}
