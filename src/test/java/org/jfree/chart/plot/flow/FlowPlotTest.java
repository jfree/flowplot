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
 * FlowPlotTests.java
 * ------------------
 * (C) Copyright 2021, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.plot.flow;

import org.jfree.chart.TestUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link FlowPlot} class.
 */
public class FlowPlotTest {

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        FlowPlot p1 = new FlowPlot(null);
        FlowPlot p2 = new FlowPlot(null);
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));

        // test fields one by one TODO
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        FlowPlot p1 = new FlowPlot(null);
        FlowPlot p2 = TestUtils.clone(p1);
        assertTrue(p1 != p2);
        assertTrue(p1.getClass() == p2.getClass());
        assertTrue(p1.equals(p2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        FlowPlot p1 = new FlowPlot(null);
        FlowPlot p2 = TestUtils.serialised(p1);
        assertEquals(p1, p2);
    }
 
}
