package rock.training.xdesign.services;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rock.training.xdesign.model.MunroDataSet;
import rock.training.xdesign.model.SearchRequest;
import rock.training.xdesign.storage.DataAccessor;
import rock.training.xdesign.utils.MunroUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MunroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunroService.class);

    private DataAccessor dataAccessor;
    private final Predicate<Map<MunroDataSet, String>> baseFilter = x -> true;
    private final Comparator<Map<MunroDataSet, String>> baseComparator = Comparator.comparing(item -> 1);

    public MunroService() {
        dataAccessor = new DataAccessor();
    }

    public List<Map<String, String>> searchMunros(SearchRequest request) {
        List<Map<String, String>> result = dataAccessor.getRecords().stream()
                .filter(extractFilterFromRequest(request))
                .sorted(extractComparatorFromRequest(request))
                .map(MunroUtils::simplifyMunroMap)
                .collect(Collectors.toList());

        if(StringUtils.isNotBlank(request.getLimitRows())) {
            int limit = Integer.parseInt(request.getLimitRows());
            result = result.subList(0, limit);
        }

        return result;
    }

    private Comparator<Map<MunroDataSet, String>> extractComparatorFromRequest(SearchRequest request) {
        Comparator<Map<MunroDataSet, String>> comparator = baseComparator;

        if(StringUtils.isNotBlank(request.getSortHeight())) {
            Comparator<Map<MunroDataSet, String>> heightComp = Comparator.comparing(item ->
                    StringUtils.isNotBlank(item.get(MunroDataSet.HEIGHT_M)) ? new BigDecimal(item.get(MunroDataSet.HEIGHT_M)) : BigDecimal.ZERO);
            if(SearchRequest.DESCENDING.equals(request.getSortHeightDirection())) {
                heightComp = heightComp.reversed();
            }
            comparator = comparator.thenComparing(heightComp);
        }
        if(StringUtils.isNotBlank(request.getSortName())) {
            Comparator<Map<MunroDataSet, String>> nameComp = Comparator.comparing(item ->
                    StringUtils.isNotBlank(item.get(MunroDataSet.NAME)) ? item.get(MunroDataSet.NAME) : StringUtils.EMPTY);
            if(SearchRequest.DESCENDING.equals(request.getSortNameDirection())) {
                nameComp = nameComp.reversed();
            }
            comparator = comparator.thenComparing(nameComp);
        }

        return comparator;
    }
    private Predicate<Map<MunroDataSet, String>> extractFilterFromRequest(SearchRequest request) {
        Predicate<Map<MunroDataSet, String>> filter = baseFilter;

        if(StringUtils.isNotBlank(request.getHillCategory())) {
            filter = filter.and(filterCategory(request.getHillCategory()));
        }
        if(StringUtils.isNotBlank(request.getMaxHeight())) {
            filter = filter.and(filterMaxHeight(new BigDecimal(request.getMaxHeight())));
        }
        if(StringUtils.isNotBlank(request.getMinHeight())) {
            filter = filter.and(filterMinHeight(new BigDecimal(request.getMinHeight())));
        }

        return filter;
    }

    private Predicate<Map<MunroDataSet, String>> filterCategory(String category) {
        return item -> StringUtils.isNotBlank(item.get(MunroDataSet.POST_1997)) && item.get(MunroDataSet.HEIGHT_M).equals(category);
    }

    private Predicate<Map<MunroDataSet, String>> filterMaxHeight(BigDecimal maxHeight) {
        return item -> StringUtils.isNotBlank(item.get(MunroDataSet.HEIGHT_M)) && new BigDecimal(item.get(MunroDataSet.HEIGHT_M)).compareTo(maxHeight) < 0;
    }

    private Predicate<Map<MunroDataSet, String>> filterMinHeight(BigDecimal minHeight) {
        return item -> StringUtils.isNotBlank(item.get(MunroDataSet.HEIGHT_M)) && new BigDecimal(item.get(MunroDataSet.HEIGHT_M)).compareTo(minHeight) > 0;
    }
}
