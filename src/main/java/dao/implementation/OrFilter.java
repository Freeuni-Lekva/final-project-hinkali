package dao.implementation;

import dao.interfaces.Filter;

import java.util.ArrayList;
import java.util.List;

public class OrFilter implements Filter{

    private List<Filter> filterList;

    public OrFilter(List<Filter> filterList){
        this.filterList = filterList;
    }

    public OrFilter(){
        filterList = new ArrayList<>();
    }

    public void addFilter(Filter f){
        filterList.add(f);
    }

    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filterList.size(); i++) {
            sb.append(filterList.get(i).format());
            if (i < filterList.size() - 1) sb.append(" OR ");
        }

        return sb.toString();
    }
}
