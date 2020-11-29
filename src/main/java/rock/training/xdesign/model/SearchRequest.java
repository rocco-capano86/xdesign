package rock.training.xdesign.model;

public class SearchRequest {

    public static final String ASCENDING = "ASC";
    public static final String DESCENDING = "DES";

    private String hillCategory;
    private String maxHeight;
    private String minHeight;
    private String limitRows;
    private String sortName;
    private String sortNameDirection;
    private String sortHeight;
    private String sortHeightDirection;

    public String getHillCategory() {
        return hillCategory;
    }

    public void setHillCategory(String hillCategory) {
        this.hillCategory = hillCategory;
    }

    public String getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(String minHeight) {
        this.minHeight = minHeight;
    }

    public String getLimitRows() {
        return limitRows;
    }

    public void setLimitRows(String limitRows) {
        this.limitRows = limitRows;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortHeight() {
        return sortHeight;
    }

    public void setSortHeight(String sortHeight) {
        this.sortHeight = sortHeight;
    }

    public String getSortNameDirection() {
        return sortNameDirection;
    }

    public void setSortNameDirection(String sortNameDirection) {
        this.sortNameDirection = sortNameDirection;
    }

    public String getSortHeightDirection() {
        return sortHeightDirection;
    }

    public void setSortHeightDirection(String sortHeightDirection) {
        this.sortHeightDirection = sortHeightDirection;
    }


}
