package ge.edu.freeuni.sdp.iot.switches.heating.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nika on 6/23/16.
 */
@XmlRootElement
public class House {

    @XmlElement
    private ConcurrentHashMap<Integer, Switch> switchMap;

    @XmlElement
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
