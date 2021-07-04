package dao.implementation;

import dao.interfaces.Filter;

public class StringFilter implements Filter {
    private final String columnLabel, filter;

    public StringFilter(String columnLabel, String filter){
        this.columnLabel = columnLabel;
        this.filter = filter;
    }


    @Override
    public String format() {
        return "'" + columnLabel + "' LIKE " + "'" + filter + "' ";
    }
}
