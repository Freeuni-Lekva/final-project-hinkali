package dao.implementation.filters;

import dao.interfaces.Filter;

public class NoFilter implements Filter {

    @Override
    public String format() {
        return "";
    }
}
