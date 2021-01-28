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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.flow.labels.DefaultFlowLabelGenerator;
import org.jfree.chart.plot.flow.labels.FlowLabelGenerator;
import org.jfree.chart.text.TextUtils;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.FlowDatasetUtils;
import org.jfree.data.flow.FlowKey;
import org.jfree.data.flow.NodeKey;

/**
 * A plot for visualising flows.
 */
public class FlowPlot extends Plot implements Cloneable, PublicCloneable, 
        Serializable {

    /** The source of data. */
    private FlowDataset dataset;
    
    /** 
     * The percentage of the plot width to assign to a gap between the nodes
     * and the flow representation. 
     */
    private double flowMargin = 0.005;
    
    private double nodeWidth = 20.0;
    
    /** The gap between nodes (expressed as a percentage of the plot height). */
    private double nodeMargin = 0.01;

    private Map<NodeKey, Color> nodeColorMap;
    
    private boolean autoPopulateNodeColors = true;
    
    private Color[] nodeColorPool = FlowColors.createBlueOceanColors();
    
    private int nodeColorPoolPointer = 0;

    /** The default node color if nothing is defined in the nodeColorMap. */
    private Color defaultNodeColor;

    private Font defaultNodeLabelFont;
    
    private Paint defaultNodeLabelPaint;
    
    private VerticalAlignment nodeLabelAlignment;
    
    private double nodeLabelOffsetX;
    
    private double nodeLabelOffsetY;
    
    private FlowLabelGenerator toolTipGenerator; 
    
    /**
     * Creates a new instance that will source data from the specified dataset.
     * 
     * @param dataset  the dataset. 
     */
    public FlowPlot(FlowDataset dataset) {
        this.dataset = dataset;
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        this.nodeColorMap = new HashMap<>();
        this.defaultNodeColor = Color.GRAY;
        this.defaultNodeLabelFont = new Font(Font.DIALOG, Font.BOLD, 12);
        this.defaultNodeLabelPaint = Color.BLACK;
        this.nodeLabelAlignment = VerticalAlignment.CENTER;
        this.nodeLabelOffsetX = 2.0;
        this.nodeLabelOffsetY = 2.0;
        this.toolTipGenerator = new DefaultFlowLabelGenerator();
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
    
    public double getFlowMargin() {
        return this.flowMargin;
    }
    
    public void setFlowMargin(double margin) {
        this.flowMargin = margin;
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
    
    public void setNodeColorPool(Color[] pool) {
        Args.nullNotPermitted(pool, "pool");
        this.nodeColorPool = pool;
    }
    
    /**
     * Returns the default node color.
     * 
     * @return The default node color (never {@code null}). 
     */
    public Color getDefaultNodeColor() {
        return this.defaultNodeColor;
    }
    
    /**
     * Sets the default node color and sends a change event to registered
     * listeners.
     * 
     * @param color  the color ({@code null} not permitted). 
     */
    public void setDefaultNodeColor(Color color) {
        Args.nullNotPermitted(color, "color");
        this.defaultNodeColor = color;
        fireChangeEvent();
    }

    /**
     * Performs a lookup on the color for the specified node.
     * 
     * @param nodeKey  the node key ({@code null} not permitted).
     * 
     * @return The node color. 
     */
    protected Color lookupNodeColor(NodeKey nodeKey) {
        Color result = this.nodeColorMap.get(nodeKey);
        if (result == null) {
            // we can either auto-populate the color or return the default
            if (this.autoPopulateNodeColors) {
                for (int s = 0; s < nodeKey.getStage(); s++) {
                    for (Object key : dataset.getSources(s)) {
                        if (nodeKey.getNode().equals(key)) {
                            Color color = this.nodeColorMap.get(new NodeKey(s, (Comparable) key));
                            setNodeFillColor(nodeKey, color);
                            return color;
                        }
                    }
                }
                
                int index = this.nodeColorPoolPointer + 1;
                if (index >= this.nodeColorPool.length) { 
                    index = 0;
                }
                setNodeFillColor(nodeKey, nodeColorPool[index]);
                this.nodeColorPoolPointer = index;
                return nodeColorPool[index];
            } else {
                result = this.defaultNodeColor;
            }
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

    /**
     * Render the plot graphics within the specified area.
     * 
     * @param g2  the graphics target ({@code null} not permitted).
     * @param area  the plot area ({@code null} not permitted).
     * @param anchor  the anchor point (ignored).
     * @param parentState  the parent state (ignored).
     * @param info  the plot rendering info.
     */
    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        Args.nullNotPermitted(g2, "g2");
        Args.nullNotPermitted(area, "area");
        
        info.setPlotArea(area);
        EntityCollection entities = info.getOwner().getEntityCollection();
        RectangleInsets insets = getInsets();
        insets.trim(area);        
        info.setDataArea(area);
        
        // use default JFreeChart background handling
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
        double flowOffset = area.getWidth() * this.flowMargin;
        
        Map<NodeKey, Rectangle2D> nodeRects = new HashMap<>();
        
        // iterate over all the stages, we can render the source node rects and
        // the flows ... we should add the destination node rects last, then
        // in a final pass add the labels
        for (int stage = 0; stage < this.dataset.getStageCount(); stage++) {
            
            double stageLeft = area.getX() + (stage + 1) * this.nodeWidth + (stage * stageWidth);
            double stageRight = stageLeft + stageWidth;
            
            // calculate the source node and flow rectangles
            double margin = this.nodeMargin * area.getHeight();
            double availableHeight = area.getHeight() - ((maxNodes-1) * margin);
            Map<FlowKey, Rectangle2D> sourceFlowRects = new HashMap<>();
            double nodeY = area.getY();
            for (Object s : this.dataset.getSources(stage)) {
                Comparable source = (Comparable) s;
                double inflow = FlowDatasetUtils.calculateInflow(dataset, source, stage);
                double outflow = FlowDatasetUtils.calculateOutflow(dataset, source, stage);
                double nodeHeight = (Math.max(inflow, outflow) / maxFlowSpace) * availableHeight;
                Rectangle2D nodeRect = new Rectangle2D.Double(stageLeft - nodeWidth, nodeY, nodeWidth, nodeHeight);
                if (entities != null) {
                    entities.add(new NodeEntity(nodeRect, source.toString()));                
                }
                nodeRects.put(new NodeKey(stage, source), nodeRect);
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
            Map<FlowKey, Rectangle2D> destFlowRects = new HashMap<>();
            nodeY = area.getY();
            for (Object d : this.dataset.getDestinations(stage)) {
                Comparable destination = (Comparable) d;
                double inflow = FlowDatasetUtils.calculateInflow(dataset, destination, stage + 1);
                double outflow = FlowDatasetUtils.calculateOutflow(dataset, destination, stage + 1);
                double nodeHeight = (Math.max(inflow, outflow) / maxFlowSpace) * availableHeight;
                nodeRects.put(new NodeKey(stage + 1, destination), new Rectangle2D.Double(stageRight, nodeY, nodeWidth, nodeHeight));
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
        
            for (Object s : this.dataset.getSources(stage)) {
                Comparable source = (Comparable) s;
                NodeKey nodeKey = new NodeKey(stage, source);
                Rectangle2D nodeRect = nodeRects.get(nodeKey);
                g2.setPaint(lookupNodeColor(nodeKey));
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
                    Color nc = lookupNodeColor(nodeKey); 
                    GradientPaint gp = new GradientPaint((float) sourceRect.getMaxX(), 0, nc, (float) destRect.getMinX(), 0, new Color(nc.getRed(), nc.getGreen(), nc.getBlue(), 128));
                    Composite saved = g2.getComposite();
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                    g2.setPaint(gp);
                    g2.fill(connect);
                    if (entities != null) {
                        entities.add(new FlowEntity(connect, this.toolTipGenerator.generateLabel(this.dataset, flowKey), ""));                
                    }
                    g2.setComposite(saved);
                }
                
            }
        }
        
        // now draw the destination nodes
        int lastStage = this.dataset.getStageCount() - 1;
        for (Object d : this.dataset.getDestinations(lastStage)) {
            Comparable destination = (Comparable) d;
            NodeKey nodeKey = new NodeKey(lastStage + 1, destination);
            Rectangle2D nodeRect = nodeRects.get(nodeKey);
            if (nodeRect != null) {
                g2.setPaint(lookupNodeColor(nodeKey));
                g2.fill(nodeRect);
                if (entities != null) {
                    entities.add(new ChartEntity(nodeRect, destination.toString()));                
                }

            }
        }
        
        // now draw all the labels over top of everything else
        g2.setFont(this.defaultNodeLabelFont);
        g2.setPaint(this.defaultNodeLabelPaint);
        for (NodeKey key : nodeRects.keySet()) {
            Rectangle2D r = nodeRects.get(key);
            if (key.getStage() < this.dataset.getStageCount()) {
                TextUtils.drawAlignedString(key.getNode().toString(), g2, (float) (r.getMaxX() + flowOffset + this.nodeLabelOffsetX), (float) (labelY(r)), TextAnchor.CENTER_LEFT);                
            } else {
                TextUtils.drawAlignedString(key.getNode().toString(), g2, (float) (r.getX() - flowOffset - this.nodeLabelOffsetX), (float) (labelY(r)), TextAnchor.CENTER_RIGHT);                
            }
        }
    }
    
    /**
     * Computes the y-coordinate for a node label taking into account the 
     * current alignment settings.
     * 
     * @param r  the node rectangle.
     * 
     * @return The y-coordinate for the label. 
     */
    private double labelY(Rectangle2D r) {
        if (this.nodeLabelAlignment == VerticalAlignment.TOP) {
            return r.getY() + this.nodeLabelOffsetY;
        } else if (this.nodeLabelAlignment == VerticalAlignment.BOTTOM) {
            return r.getMaxY() - this.nodeLabelOffsetY;
        } else {
            return r.getCenterY();
        }
    }
}
