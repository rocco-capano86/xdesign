package rock.training.xdesign.utils;

import rock.training.xdesign.model.MunroDataSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MunroUtils {

    public static final List<MunroDataSet> HEADERS = Arrays.asList(MunroDataSet.values());
    public static final List<MunroDataSet> HEADERS_RESPONSE = Arrays.asList(MunroDataSet.NAME, MunroDataSet.HEIGHT_M, MunroDataSet.POST_1997, MunroDataSet.GRID_REF);

    public static Map<MunroDataSet, String> transformToMap(List<String> inputRow) {
        Map<MunroDataSet, String> record = new HashMap<>();

        HEADERS.forEach(h -> record.put(h, inputRow.get(record.size())));

        return record;
    }

    public static Map<String, String> simplifyMunroMap(Map<MunroDataSet, String> inputRow) {
        Map<String, String> record = new HashMap<>();

        HEADERS_RESPONSE.forEach(h -> record.put(h.getKey(), inputRow.get(h)));

        return record;
    }
}
