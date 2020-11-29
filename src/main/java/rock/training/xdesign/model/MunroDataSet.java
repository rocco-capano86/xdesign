package rock.training.xdesign.model;

import java.util.Arrays;

public enum MunroDataSet {
    
    MUNRO_ID("Running No"),
    DOBIH("DoBIH Number"),
    STREETMAP("Streetmap"),
    GEOGRAPH("Geograph"),
    HILL_BAG("Hill-bagging"),
    NAME("Name"),
    SMC("SMC Section"),
    RHB("RHB Section"),
    SECTION("_Section"),
    HEIGHT_M("Height (m)"),
    HEIGHT_F("Height (ft)"),
    MAP_150("Map 1:50"),
    MAP_125("Map 1:25"),
    GRID_REF("Grid Ref"),
    GRID_XY("GridRefXY"),
    X_COORD("xcoord"),
    Y_COORD("ycoord"),
    K_1891("1891"),
    K_1921("1921"),
    K_1933("1933"),
    K_1953("1953"),
    K_1969("1969"),
    K_1974("1974"),
    K_1981("1981"),
    K_1984("1984"),
    K_1990("1990"),
    K_1997("1997"),
    POST_1997("Post 1997"),
    COMMENTS("Comments");

    private String key;
    
    MunroDataSet(String key) {
        this.key = key;
    }

    public static MunroDataSet findByKey(String key) {
        return Arrays.stream(MunroDataSet.values()).filter(munro -> munro.getKey().equals(key)).findFirst().orElse(null);
    }

    public String getKey() {
        return key;
    }
}
