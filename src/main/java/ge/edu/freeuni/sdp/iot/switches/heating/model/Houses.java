package ge.edu.freeuni.sdp.iot.switches.heating.model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nika on 6/23/16.
 */
public class Houses {

    private static Houses instance;

    public static Houses getInstance() {
        if (instance == null) {
            synchronized (Houses.class) {
                if (instance == null)
                    instance = new Houses();
            }
        }

        return instance;
    }

    private ConcurrentHashMap<Integer, House> houseMap;

    public Houses() {
        houseMap = new ConcurrentHashMap<>();
    }

    public void put(House house) {
        houseMap.put(house.getHouseId(), house);
    }

    public House get(int houseId) {
        return houseMap.get(houseId);
    }

}
