package rock.training.xdesign.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rock.training.xdesign.model.SearchResponse;
import rock.training.xdesign.services.MunroService;

import static org.junit.jupiter.api.Assertions.*;

class SearchMunrosTest {

    @InjectMocks
    private SearchMunros controller;

    @Mock
    private MunroService munroService;

    @BeforeEach
    public void init() {
        controller = new SearchMunros();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void searchMunros() {
        SearchResponse response = controller.searchMunros(null);
        assertEquals(SearchMunros.STATUS_SUCCESS, response.getStatus());
    }
}