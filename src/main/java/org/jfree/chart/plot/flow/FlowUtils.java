/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfree.chart.plot.flow;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.ObjectUtils;

/**
 *
 * @author dgilbert
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
