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
    private ConcurrentHashMap<Integer, Switch> switches;

    @XmlElement
    private int houseId;

    public House(int houseId) {
        this.houseId = houseId;
        switches = new ConcurrentHashMap<>();
        switches.put(1, new Switch(1, true));
        switches.put(2, new Switch(2, false));
    }

    public int getHouseId() {
        return houseId;
    }

    public void add(Switch s) {
        switches.put(s.getFloorId(), s);
    }

    public Switch get(Integer switchId) {
        return switches.get(switchId);
    }

    public Collection<Switch> getValues() {
        return switches.values();
    }

}
