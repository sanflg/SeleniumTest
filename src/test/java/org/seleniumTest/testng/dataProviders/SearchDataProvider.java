package org.seleniumTest.testng.dataProviders;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
    @DataProvider(name = "data-provider")
    public static Object[][] dpMethod() {
        Object[] searches = new Object[]{"button", "submit", "enter"};
        Object[] terms = new Object[]{"panda", "staircase"};
        //TODO there is a less ugly way to do a product of 1D matrices wih daaProviders?
        Object[][] matrix = new Object[searches.length + terms.length + 1][2];
        for (int i = 0; i < matrix.length;) {
            for (int j = 0; j < 2; j++) {
                matrix[i][0] = searches[i/2];
                matrix[i][1] = terms[j];
                i++;
            }
        }
        return matrix;
    }
}

