package ge.edu.freeuni.sdp.iot.switches.heating.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nika on 6/23/16.
 */
@XmlRootElement
public class Switch {

    @XmlElement
    private int floorId;

    @XmlElement
    private Boolean status;

    public Switch(int id, Boolean status) {
        this.floorId = id;
        this.status = status;
    }

    public int getFloorId() {
        return floorId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
