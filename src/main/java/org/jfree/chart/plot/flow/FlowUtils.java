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
 * --------------
 * FlowUtils.java
 * --------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.plot.flow;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.ObjectUtils;

/**
 * Utility methods used by the {@link FlowPlot} class.
 */
public class FlowUtils {

    /**
     * Returns a list containing cloned copies of the items in the source
     * list.
     * 
     * @param source  the source list ({@code null} not permitted).
     * 
     * @return A new list. 
     */
    public static List<?> cloneList(List<?> source) {
        Args.nullNotPermitted(source, "source");
        List result = new ArrayList();
        for (Object obj: source) {
            if (obj != null) {
                if (obj instanceof String) {
                    result.add(obj);
                } else {
                    try {
                        result.add(ObjectUtils.clone(obj));
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                result.add(null);
            }
        }
        return result;
    }

}
