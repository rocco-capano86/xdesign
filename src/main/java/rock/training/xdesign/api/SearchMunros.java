package rock.training.xdesign.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rock.training.xdesign.model.SearchRequest;
import rock.training.xdesign.model.SearchResponse;
import rock.training.xdesign.services.MunroService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SearchMunros {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchMunros.class);
    public static final String STATUS_SUCCESS = "SUCCESS";

    @Autowired
    private MunroService munroService;

    @RequestMapping(value = "/search",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResponse searchMunros(SearchRequest request) {
        SearchResponse response = new SearchResponse();

        try {
            //TODO validation input
            List<Map<String, String>> results = munroService.searchMunros(request);
            response.setStatus(STATUS_SUCCESS);
            response.setResults(results);
        } catch (Exception e) {
            setErrorResponse(response);
            LOGGER.error("Error during call to service munroService", e);
        }

        return response;
    }

    private void setErrorResponse(SearchResponse response) {
        response.setStatus("FAIL");
        response.setErrorCode("001");
        response.setErrorDescription("Unexpected Error");
    }
}
