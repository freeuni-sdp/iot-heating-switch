package ge.edu.freeuni.sdp.iot.switches.heating.model;

/**
 * Created by nika on 6/23/16.
 */
public class Switch {

    private int floorId;
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
