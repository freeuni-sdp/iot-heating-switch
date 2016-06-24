package ge.edu.freeuni.sdp.iot.switches.heating.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by nika on 6/23/16.
 */
@XmlRootElement
public class Switch {

    @XmlElement
    private int id;

    @XmlElement
    private Boolean status;

    @XmlElement
    private Boolean available;

    public Switch(int id, Boolean status) {
        this.id = id;
        this.status = status;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }

}
