package model;

import commons.beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
    List<UserBean> resultList;

    public SearchResults(){
        resultList = new ArrayList<>();
    }

    public void addEntry(UserBean userBean){
        resultList.add(userBean);
    }

    public SearchResults(List<UserBean> daoResults){
        this.resultList = daoResults;
    }

    public List<UserBean> getResultList() {
        return resultList;
    }
}
