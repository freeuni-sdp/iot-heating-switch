package ge.edu.freeuni.sdp.iot.switches.heating.core;

/**
 * Created by nika on 6/25/16.
 */
public class HouseRegistryFactory {

    private static final String REGISTRY_URL = "https://iot-house-registry.herokuapp.com/houses/";

    private static HouseRegistry entry;

    public static HouseRegistry getHouseRegistry() {
        if (entry == null) {
            synchronized (HouseRegistryFactory.class) {
                if (entry == null)
                    entry = buildHouseRegistry();
            }
        }

        return entry;
    }

    private static HouseRegistry buildHouseRegistry() {
        return new HouseOnlineRegistry(REGISTRY_URL);
    }

}
