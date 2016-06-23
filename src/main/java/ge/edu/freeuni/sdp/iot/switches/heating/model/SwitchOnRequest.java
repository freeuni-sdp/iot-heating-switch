package ge.edu.freeuni.sdp.iot.switches.heating.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nika on 6/23/16.
 */
@XmlRootElement
public class SwitchOnRequest {

    @XmlElement
    private int period;

    public int getPeriod() {
        return period;
    }

}
