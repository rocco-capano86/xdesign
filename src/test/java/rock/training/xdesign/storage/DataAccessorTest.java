package rock.training.xdesign.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessorTest {

    @Test
    public void extractDataSet() {
        DataAccessor da = new DataAccessor();
        Assertions.assertNotNull(da.getRecords());
    }

}