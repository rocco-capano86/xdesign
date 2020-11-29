package rock.training.xdesign.storage;

import org.slf4j.Logger;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.LoggerFactory;
import rock.training.xdesign.model.MunroDataSet;
import rock.training.xdesign.utils.MunroUtils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DataAccessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessor.class);

    private List<Map<MunroDataSet, String>> records = new ArrayList<>();

    public DataAccessor() {
        //eventually setup property
        loadData("./munrotab_v6.2.csv");
    }

    private void loadData(String filePath) {

        URL res = DataAccessor.class.getClassLoader().getResource(filePath);
        try (CSVReader csvReader = new CSVReader(new FileReader(res.getFile()));) {
            boolean headerProcessed = false;
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                if (headerProcessed) {
                    records.add(MunroUtils.transformToMap(Arrays.asList(values)));
                } else {

                    headerProcessed = true;
                }
            }
        } catch (IOException|CsvValidationException e) {
            LOGGER.error("Exception during Data Set init!", e);
        }
        LOGGER.info("Loaded DataSet: {}", records);
    }

    public List<Map<MunroDataSet, String>> getRecords() {
        return records;
    }
}
