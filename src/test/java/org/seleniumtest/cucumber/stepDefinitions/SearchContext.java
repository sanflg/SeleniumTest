package org.seleniumtest.cucumber.stepDefinitions;

public class SearchContext {
    private String term;

    public SearchContext(){}

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
