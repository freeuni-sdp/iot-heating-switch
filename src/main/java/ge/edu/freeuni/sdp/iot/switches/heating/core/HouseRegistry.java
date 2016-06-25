package ge.edu.freeuni.sdp.iot.switches.heating.core;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;

import javax.ws.rs.NotFoundException;


/**
 * Created by nika on 6/25/16.
 */
public interface HouseRegistry {

    House getHouse(String houseId);

    Switch getSwitch(String houseId, String switchId);

    boolean switchOn(String houseId, String switchId, SwitchOnRequest request);

    boolean switchOff(String houseId, String switchId);

}
