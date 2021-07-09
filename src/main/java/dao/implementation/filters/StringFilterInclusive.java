package dao.implementation.filters;

import dao.interfaces.Filter;

public class StringFilterInclusive implements Filter {
    private final String columnLabel, filter;

    public StringFilterInclusive(String columnLabel, String filter){
        this.columnLabel = columnLabel;
        this.filter = filter;
    }


    @Override
    public String format() {
        if (columnLabel.isEmpty() || filter.isEmpty()) return "";
        return columnLabel + " LIKE " + "'%" + filter + "%'";
    }
}
