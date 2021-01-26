/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2020, by Object Refinery Limited and Contributors.
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
 * TestUtils.java
 * --------------
 * (C) Copyright 2007-2020, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import org.jfree.chart.util.PublicCloneable;

/**
 * Some utility methods for use by the testing code.
 */
public class TestUtils {

    /**
     * Returns {@code true} if the collections contains any object that
     * is an instance of the specified class, and {@code false} otherwise.
     *
     * @param collection  the collection.
     * @param c  the class.
     *
     * @return A boolean.
     */
    public static boolean containsInstanceOf(Collection<?> collection, Class c) {
        for (Object obj : collection) {
            if (obj != null && obj.getClass().equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Serialises an object, deserialises it and returns the deserialised 
     * version.
     * 
     * @param original  the original object.
     * 
     * @return A serialised and deserialised version of the original.
     */
    public static <K> K serialised(K original) {
        K result = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(buffer);
            out.writeObject(original);
            out.close();
            ObjectInput in = new ObjectInputStream(
                    new ByteArrayInputStream(buffer.toByteArray()));
            result = (K) in.readObject();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Returns a clone of the specified object, if it can be cloned, otherwise
     * throws a {@code CloneNotSupportedException}.  If the object is 
     * {@code null} this method returns {@code null}.
     *
     * @param object the object to clone ({@code null} permitted).
     * 
     * @param <T>  the object type.
     * 
     * @return A clone of the specified object, or {@code null}.
     * 
     * @throws CloneNotSupportedException if the object cannot be cloned.
     */
    public static <T> T clone(T object) throws CloneNotSupportedException {
        if (object == null) {
            return null;
        }
        if (object instanceof PublicCloneable) {
            PublicCloneable pc = (PublicCloneable) object;
            return (T) pc.clone();
        } else {
            try {
                Method method = object.getClass().getMethod("clone",
                        (Class[]) null);
                if (Modifier.isPublic(method.getModifiers())) {
                    return (T) method.invoke(object, (Object[]) null);
                }
            } catch (NoSuchMethodException e) {
                throw new CloneNotSupportedException("Object without clone() method is impossible.");
            } catch (IllegalAccessException e) {
                throw new CloneNotSupportedException("Object.clone(): unable to call method.");
            } catch (InvocationTargetException e) {
                throw new CloneNotSupportedException("Object without clone() method is impossible.");
            }
        }
        throw new CloneNotSupportedException("Failed to clone.");
    }

}
