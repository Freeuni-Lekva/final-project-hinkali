package servlets.search;

import commons.beans.UserBean;

import java.util.List;

public class SearchResults {
    List<UserBean> resultList;

    public SearchResults(List<UserBean> daoResults){
        this.resultList = daoResults;
    }

    public List<UserBean> getResultList() {
        return resultList;
    }
}
