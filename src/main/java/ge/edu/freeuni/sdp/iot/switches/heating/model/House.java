package ge.edu.freeuni.sdp.iot.switches.heating.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nika on 6/23/16.
 */
public class House {

    private ConcurrentHashMap<Integer, Switch> switchMap;
    private int houseId;

    public House(int houseId) {
        this.houseId = houseId;
        switchMap = new ConcurrentHashMap<>();
    }

    public int getHouseId() {
        return houseId;
    }

    public void add(Switch s) {
        switchMap.put(s.getFloorId(), s);
    }

    public Switch get(Integer switchId) {
        return switchMap.get(switchId);
    }

    public Collection<Switch> getValues() {
        return switchMap.values();
    }

}
