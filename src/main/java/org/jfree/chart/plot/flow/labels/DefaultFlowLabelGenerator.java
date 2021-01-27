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
 * ------------------------------
 * DefaultFlowLabelGenerator.java
 * ------------------------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.plot.flow.labels;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.FlowKey;

/**
 * Standard flow label generator.
 */
public class DefaultFlowLabelGenerator implements FlowLabelGenerator, Cloneable, PublicCloneable, Serializable {
    
    private NumberFormat formatter;
    
    public DefaultFlowLabelGenerator() {
        this.formatter = new DecimalFormat("0.00");    
    }

    @Override
    public String generateLabel(FlowDataset dataset, FlowKey key) {
        Number n = dataset.getFlow(key.getStage(), key.getSource(), key.getDestination());
        String nStr = this.formatter.format(n);
        return key.getSource().toString() + " -> " + key.getDestination().toString() + " = " + nStr;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
