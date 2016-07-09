package ge.edu.freeuni.sdp.iot.switches.heating.core;

/**
 * Created by nika on 6/25/16.
 */
public class HouseRegistryFactory {

    private static final String REGISTRY_URL = "https://iot-house-registry.herokuapp.com/houses/";

    private static HouseRegistry entry;
    private static HouseRegistry testEntry;
    private static boolean testMode = false;

    public static HouseRegistry getHouseRegistry() {

        if (testMode) {
            return testEntry;
        }

        if (entry == null) {
            synchronized (HouseRegistryFactory.class) {
                if (entry == null)
                    entry = buildHouseRegistry();
            }
        }

        return entry;
    }

    public static void setTestMode(boolean value) {
        testMode = value;
    }

    public static void setTestEntry(HouseRegistry registry) {
        testEntry = registry;
    }

    private static HouseRegistry buildHouseRegistry() {
        return new HouseOnlineRegistry(REGISTRY_URL);
    }

}
