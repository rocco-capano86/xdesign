package rock.training.xdesign.storage;

import org.slf4j.Logger;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.LoggerFactory;
import rock.training.xdesign.model.MunroDataSet;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DataAccessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessor.class);

    private static final List<MunroDataSet> HEADERS = Arrays.asList(MunroDataSet.values());

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
                    records.add(transformToMap(Arrays.asList(values)));
                } else {

                    headerProcessed = true;
                }
            }
        } catch (IOException|CsvValidationException e) {
            LOGGER.error("Exception during Data Set init!", e);
        }
        LOGGER.info("Loaded DataSet: {}", records);
    }

    private static Map<MunroDataSet, String> transformToMap(List<String> inputRow) {
        Map<MunroDataSet, String> record = new HashMap<>();

        HEADERS.forEach(h -> record.put(h, inputRow.get(record.size())));

        return record;
    }

    public List<Map<MunroDataSet, String>> getRecords() {
        return records;
    }
}
