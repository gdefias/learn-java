package net.jcip.examples.part1_base.chapter4_objectcomb;

import net.jcip.annotations.ThreadSafe;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * DelegatingVehicleTracker


 */

//将线程安全委托给ConcurrentHashMap

@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    //返回一个不可修改但却实时的车辆位置视图，对比MonitorVehicleTracker的该方法，时好时坏取决于实际需求
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }

    //返回locations的静态拷贝而非实时拷贝
    public Map<String, Point> getLocationsAsStatic() {
        return Collections.unmodifiableMap(
                new HashMap<String, Point>(locations));
    }
}

