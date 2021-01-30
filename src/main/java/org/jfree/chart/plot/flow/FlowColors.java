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
 * ---------------
 * FlowColors.java
 * ---------------
 * (C) Copyright 2021, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.plot.flow;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that creates and returns color swatches that can be used
 * in charts.  The "i want hue" utility has been used to generate a number 
 * of these color sets.  
 * See <a href="http://tools.medialab.sciences-po.fr/iwanthue/">
 * http://tools.medialab.sciences-po.fr/iwanthue/</a>. 
 */
public class FlowColors {

    // this class is copied from the Orson Charts library
    
    private FlowColors() {
        // no need to instantiate this class
    }
    
    /**
     * Returns the default colors.
     * 
     * @return The default colors. 
     */
    public static List<Color> getDefaultColors() {
        return createShadesColors();
    }

    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Fancy (light background)" settings.  A new list 
     * instance is created for each call to this method.  A link to the 
     * "i want hue" utility is given in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createFancyLightColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(239, 164, 127));
        result.add(new Color(140, 228, 139));
        result.add(new Color(155, 208, 227));
        result.add(new Color(221, 228, 95));
        result.add(new Color(118, 223, 194));
        result.add(new Color(240, 166, 184));
        result.add(new Color(231, 185, 98));
        result.add(new Color(186, 214, 150));
        result.add(new Color(217, 184, 226));
        result.add(new Color(201, 212, 116));
        return result; 
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Fancy (dark background)" settings.  A new list 
     * instance is created for each call to this method.  A link to the 
     * "i want hue" utility is given in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createFancyDarkColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(78, 81, 97));
        result.add(new Color(91, 104, 51));
        result.add(new Color(138, 75, 65));
        result.add(new Color(72, 62, 34));
        result.add(new Color(58, 100, 75));
        result.add(new Color(39, 63, 59));
        result.add(new Color(105, 68, 75));
        result.add(new Color(120, 90, 120));
        result.add(new Color(119, 90, 50));
        result.add(new Color(59, 103, 111));
        return result; 
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Shades" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createShadesColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(137, 132, 104));
        result.add(new Color(217, 232, 208));
        result.add(new Color(53, 48, 40));
        result.add(new Color(240, 225, 172));
        result.add(new Color(196, 160, 128));
        result.add(new Color(92, 96, 87));
        result.add(new Color(136, 141, 136));
        result.add(new Color(106, 93, 66));
        result.add(new Color(205, 199, 168));
        result.add(new Color(158, 168, 143));
        return result;
    }

    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Tarnish" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createTarnishColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(148, 129, 121));
        result.add(new Color(179, 181, 136));
        result.add(new Color(204, 163, 140));
        result.add(new Color(102, 93, 80));
        result.add(new Color(164, 178, 159));
        result.add(new Color(156, 130, 100));
        result.add(new Color(129, 142, 124));
        result.add(new Color(186, 168, 159));
        result.add(new Color(144, 148, 108));
        result.add(new Color(189, 169, 131));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Pastel" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createPastelColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(232, 177, 165));
        result.add(new Color(207, 235, 142));
        result.add(new Color(142, 220, 220));
        result.add(new Color(228, 186, 115));
        result.add(new Color(187, 200, 230));
        result.add(new Color(157, 222, 177));
        result.add(new Color(234, 183, 210));
        result.add(new Color(213, 206, 169));
        result.add(new Color(202, 214, 205));
        result.add(new Color(195, 204, 133));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Pimp" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createPimpColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(197, 75, 103));
        result.add(new Color(85, 154, 48));
        result.add(new Color(122, 110, 206));
        result.add(new Color(190, 100, 50));
        result.add(new Color(201, 79, 209));
        result.add(new Color(95, 127, 170));
        result.add(new Color(147, 129, 39));
        result.add(new Color(63, 142, 96));
        result.add(new Color(186, 84, 150));
        result.add(new Color(219, 66, 52));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Intense" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createIntenseColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(107, 122, 160));
        result.add(new Color(99, 176, 67));
        result.add(new Color(214, 85, 52));
        result.add(new Color(202, 79, 200));
        result.add(new Color(184, 149, 57));
        result.add(new Color(82, 168, 146));
        result.add(new Color(194, 84, 128));
        result.add(new Color(77, 102, 50));
        result.add(new Color(132, 108, 197));
        result.add(new Color(144, 74, 61));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Fluo" settings.  A new list instance is created for
     * each call to this method.  A link to the "i want hue" utility is given 
     * in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createFluoColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(108, 236, 137));
        result.add(new Color(253, 187, 46));
        result.add(new Color(56, 236, 216));
        result.add(new Color(171, 231, 51));
        result.add(new Color(221, 214, 74));
        result.add(new Color(106, 238, 70));
        result.add(new Color(172, 230, 100));
        result.add(new Color(242, 191, 82));
        result.add(new Color(221, 233, 56));
        result.add(new Color(242, 206, 47));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Red Roses" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createRedRosesColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(230, 129, 128));
        result.add(new Color(233, 56, 39));
        result.add(new Color(225, 45, 102));
        result.add(new Color(172, 79, 55));
        result.add(new Color(214, 154, 128));
        result.add(new Color(156, 96, 81));
        result.add(new Color(190, 77, 91));
        result.add(new Color(228, 121, 91));
        result.add(new Color(216, 63, 80));
        result.add(new Color(209, 75, 46));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Ochre Sand" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createOchreSandColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(218, 180, 125));
        result.add(new Color(245, 184, 36));
        result.add(new Color(159, 103, 28));
        result.add(new Color(124, 96, 55));
        result.add(new Color(224, 132, 56));
        result.add(new Color(185, 143, 48));
        result.add(new Color(229, 171, 97));
        result.add(new Color(232, 165, 54));
        result.add(new Color(171, 102, 53));
        result.add(new Color(160, 122, 71));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Yellow Lime" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createYellowLimeColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(235, 203, 59));
        result.add(new Color(113, 108, 56));
        result.add(new Color(222, 206, 134));
        result.add(new Color(169, 166, 62));
        result.add(new Color(214, 230, 54));
        result.add(new Color(225, 221, 105));
        result.add(new Color(128, 104, 23));
        result.add(new Color(162, 151, 86));
        result.add(new Color(117, 121, 25));
        result.add(new Color(183, 179, 40));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Green Mint" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createGreenMintColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(99, 224, 113));
        result.add(new Color(98, 132, 83));
        result.add(new Color(145, 234, 49));
        result.add(new Color(181, 215, 158));
        result.add(new Color(95, 171, 43));
        result.add(new Color(100, 208, 142));
        result.add(new Color(172, 222, 84));
        result.add(new Color(75, 139, 53));
        result.add(new Color(177, 216, 123));
        result.add(new Color(83, 223, 60));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Ice Cube" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createIceCubeColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(112, 235, 233));
        result.add(new Color(54, 110, 100));
        result.add(new Color(211, 232, 208));
        result.add(new Color(94, 230, 191));
        result.add(new Color(76, 154, 155));
        result.add(new Color(156, 181, 157));
        result.add(new Color(67, 152, 126));
        result.add(new Color(112, 135, 119));
        result.add(new Color(155, 213, 192));
        result.add(new Color(80, 195, 190));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Blue Ocean" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}). 
     */
    public static List<Color> createBlueOceanColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(53, 84, 154));
        result.add(new Color(41, 46, 57));
        result.add(new Color(115, 124, 151));
        result.add(new Color(38, 52, 91));
        result.add(new Color(84, 117, 211));
        result.add(new Color(76, 125, 181));
        result.add(new Color(109, 108, 112));
        result.add(new Color(48, 105, 134));
        result.add(new Color(72, 82, 107));
        result.add(new Color(91, 99, 144));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Indigo Night" settings.  A new list instance is 
     * created for each call to this method.  A link to the "i want hue" 
     * utility is given in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createIndigoNightColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(127, 88, 147));
        result.add(new Color(201, 67, 217));
        result.add(new Color(112, 97, 218));
        result.add(new Color(219, 134, 222));
        result.add(new Color(154, 80, 172));
        result.add(new Color(170, 106, 231));
        result.add(new Color(142, 111, 210));
        result.add(new Color(194, 149, 235));
        result.add(new Color(152, 118, 188));
        result.add(new Color(214, 101, 237));
        return result;
    }
    
    /**
     * Returns a palette of ten colors created using the "i want hue" utility,
     * using the preset "Purple Wine" settings.  A new list instance is created 
     * for each call to this method.  A link to the "i want hue" utility is 
     * given in the class description.
     * 
     * @return A list of ten colors (never {@code null}).
     */
    public static List<Color> createPurpleWineColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(116, 28, 93));
        result.add(new Color(112, 79, 75));
        result.add(new Color(178, 37, 101));
        result.add(new Color(109, 24, 56));
        result.add(new Color(167, 42, 140));
        result.add(new Color(66, 30, 40));
        result.add(new Color(128, 70, 95));
        result.add(new Color(78, 20, 56));
        result.add(new Color(155, 62, 111));
        result.add(new Color(139, 61, 75));
        return result;
    }

    /**
     * Returns a palette of 7 colors with earth tones.
     * 
     * @return A list of 7 colors (never {@code null}). 
     */
     public static List<Color> getEarthColors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(98, 98, 98));
        result.add(new Color(159, 87, 43));
        result.add(new Color(194, 176, 46));
        result.add(new Color(134, 155, 64));
        result.add(new Color(57, 118, 40));
        result.add(new Color(40, 114, 110));
        result.add(new Color(78, 79, 62));
        return result;
    } 

    /**
     * Returns a newly created list containing 9 colors from the the 
     * ColorBrewer tool.  This is a high-contrast set of colors, good for
     * pie charts.
     * 
     * http://colorbrewer2.org/?type=qualitative&amp;scheme=Set1&amp;n=9
     * 
     * @return A color list.
     */
    public static List<Color> getBrewerQualitativeSet1N9Colors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(228, 26, 28));
        result.add(new Color(55, 126, 184));
        result.add(new Color(77, 175, 74));
        result.add(new Color(152, 78, 163));
        result.add(new Color(255, 127, 0));
        result.add(new Color(255, 255, 51));
        result.add(new Color(166, 86, 40));
        result.add(new Color(247, 129, 191));
        result.add(new Color(153, 153, 153));
        return result;
    }
  
    /**
     * Returns a newly created list containing 12 colors from the the 
     * ColorBrewer tool.
     * 
     * http://colorbrewer2.org/?type=qualitative&amp;scheme=Paired&amp;n=12
     * 
     * @return A color list.
     */
    public static List<Color> getBrewerQualitativePairedN12Colors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(166, 206, 227));
        result.add(new Color(31, 120, 180));
        result.add(new Color(178, 223, 138));
        result.add(new Color(51, 160, 44));
        result.add(new Color(251, 154, 153));
        result.add(new Color(227, 26, 28));
        result.add(new Color(253, 191, 111));
        result.add(new Color(255, 127, 0));
        result.add(new Color(202, 178, 214));
        result.add(new Color(106, 61, 154));
        result.add(new Color(255, 255, 153));
        result.add(new Color(177, 89, 40));
        return result;
    }
    
    /**
     * Returns a newly created list containing 11 colors from the the 
     * ColorBrewer tool.  Good for pie charts and bar charts, not so good for 
     * scatter plots.
     * 
     * http://colorbrewer2.org/?type=qualitative&amp;scheme=Set3&amp;n=12 
     * 
     * @return A color list.
     */
    public static List<Color> getBrewerQualitativeSet3N12Colors() {
        List<Color> result = new ArrayList<>(); 
        result.add(new Color(141, 211, 199));
        result.add(new Color(255, 255, 179));
        result.add(new Color(190, 186, 218));
        result.add(new Color(251, 128, 114));
        result.add(new Color(128, 177, 211));
        result.add(new Color(253, 180, 98));
        result.add(new Color(179, 222, 105));
        result.add(new Color(252, 205, 229));
        result.add(new Color(217, 217, 217));
        result.add(new Color(188, 128, 189));
        result.add(new Color(204, 235, 197));
        result.add(new Color(255, 237, 111));
        return result;
    }

    /**
     * Returns a list of colors sourced from 
     * http://www.sapdesignguild.org/goodies/diagram_guidelines/index.html.
     * 
     * @return A color list.
     */
    public static List<Color> getSAPMultiColor() {
        List<Color> result = new ArrayList<>();
        result.add(new Color(255, 248, 163));
        result.add(new Color(169, 204, 143));
        result.add(new Color(178, 200, 217));
        result.add(new Color(190, 163, 122));
        result.add(new Color(243, 170, 121));
        result.add(new Color(181, 181, 169));
        result.add(new Color(230, 165, 164));
        result.add(new Color(248, 215, 83));
        result.add(new Color(92, 151, 70));
        result.add(new Color(62, 117, 167));
        result.add(new Color(122, 101, 62));
        result.add(new Color(225, 102, 42));
        result.add(new Color(116, 121, 111));
        result.add(new Color(196, 56, 79));
        return result;
    }
    
    /**
     * Returns a list of four colors.
     * 
     * @return A list of four colors. 
     */
    public static List<Color> getColors1() {
        List<Color> result = new ArrayList<>();
        result.add(new Color(0, 55, 122));
        result.add(new Color(24, 123, 58));
        result.add(Color.RED); 
        result.add(Color.YELLOW); 
        return result;
    }
    
    /**
     * Returns a list of four colors.
     * 
     * @return A list of four colors. 
     */
    public static List<Color> getColors2() {
        List<Color> result = new ArrayList<>();
        result.add(new Color(0x1A9641));
        result.add(new Color(0xA6D96A));
        result.add(new Color(0xFDAE61));
        result.add(new Color(0xFFFFBF));
        return result;
    }
        
    /**
     * Returns a list of six colors 
     * (source: http://blog.design-seeds.com/generating-color/).
     * 
     * @return A list of six colors. 
     */
    public static List<Color> getDesignSeedsShells() {
        List<Color> result = new ArrayList<>();
        result.add(new Color(228, 233, 239));
        result.add(new Color(184, 197, 219));
        result.add(new Color(111, 122, 143));
        result.add(new Color(95, 89, 89));
        result.add(new Color(206, 167, 145));
        result.add(new Color(188, 182, 173));
        return result;
    }
    
    /**
     * Returns a list of six colors 
     * (source: http://blog.design-seeds.com/generating-color/).
     * 
     * @return A list of six colors. 
     */
    public static List<Color> getDesignSeedsPepper() {
        List<Color> result = new ArrayList<>();
        result.add(new Color(255, 219, 142));
        result.add(new Color(220, 21, 20));
        result.add(new Color(149, 0, 1));
        result.add(new Color(82, 102, 41));
        result.add(new Color(142, 101, 72));
        result.add(new Color(199, 169, 128));
        return result;
    }
}

