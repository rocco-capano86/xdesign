package rock.training.xdesign.services;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rock.training.xdesign.model.MunroDataSet;
import rock.training.xdesign.model.SearchRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MunroServiceTest {

    private MunroService service;

    @BeforeEach
    public void init() {
        service = new MunroService();
    }

    @Test
    public void searchMunros() {
        List<Map<String, String>> result = service.searchMunros(new SearchRequest());
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void searchMunrosMaxHeightMinHeight() {
        BigDecimal maxHeight = new BigDecimal("975");
        BigDecimal minHeight = new BigDecimal("900");
        SearchRequest req = new SearchRequest();
        req.setMaxHeight(maxHeight.toPlainString());
        req.setMinHeight(minHeight.toPlainString());
        List<Map<String, String>> result = service.searchMunros(req);
        assertNotNull(result);

        for(Map<String, String> item : result) {
            String height = item.get(MunroDataSet.HEIGHT_M.getKey());
            assertTrue(maxHeight.compareTo(new BigDecimal(height)) > 0);
            assertTrue(minHeight.compareTo(new BigDecimal(height)) < 0);
        }
    }

    @Test
    public void searchMunrosCategory() {
        SearchRequest req = new SearchRequest();
        req.setHillCategory("MUN");
        List<Map<String, String>> result = service.searchMunros(req);
        assertNotNull(result);

        for(Map<String, String> item : result) {
            String category = item.get(MunroDataSet.POST_1997.getKey());
            assertEquals("MUN", category);
        }
    }

    @Test
    public void searchMunrosTopTen() {
        SearchRequest req = new SearchRequest();
        req.setLimitRows("10");
        List<Map<String, String>> result = service.searchMunros(req);
        assertNotNull(result);
        assertTrue(result.size() == 10);
    }

    @Test
    public void searchMunrosOrderedHeight() {
        SearchRequest req = new SearchRequest();
        req.setSortHeight("TRUE");
        List<Map<String, String>> result = service.searchMunros(req);
        assertNotNull(result);

        BigDecimal maxHeight = BigDecimal.ZERO;
        for(Map<String, String> item : result) {
            String height = item.get(MunroDataSet.HEIGHT_M.getKey());
            BigDecimal numberHeight = StringUtils.isNotBlank(height) ? new BigDecimal(height) : BigDecimal.ZERO;
            assertTrue(maxHeight.compareTo(numberHeight) <= 0);
            maxHeight = numberHeight;
        }
    }

    @Test
    public void searchMunrosOrderedHeightDescending() {
        SearchRequest req = new SearchRequest();
        req.setSortHeight("TRUE");
        req.setSortHeightDirection(SearchRequest.DESCENDING);
        List<Map<String, String>> result = service.searchMunros(req);
        assertNotNull(result);

        BigDecimal maxHeight = new BigDecimal("1000000");
        for(Map<String, String> item : result) {
            String height = item.get(MunroDataSet.HEIGHT_M.getKey());
            BigDecimal numberHeight = StringUtils.isNotBlank(height) ? new BigDecimal(height) : BigDecimal.ZERO;
            assertTrue(maxHeight.compareTo(numberHeight) >= 0);
            maxHeight = numberHeight;
        }
    }
}