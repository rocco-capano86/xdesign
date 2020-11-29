package rock.training.xdesign.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MunroServiceTest {

    private MunroService service;

    @BeforeEach
    public void init() {
        service = new MunroService();
    }

    @Test
    void searchMunros() {
        List<Map<String, String>> result = service.searchMunros();
        Assertions.assertNotNull(result);
    }
}