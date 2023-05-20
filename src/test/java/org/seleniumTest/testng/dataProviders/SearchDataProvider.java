package org.seleniumTest.testng.dataProviders;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
    @DataProvider(name = "button")
    public static Object[][] button() {
        return new Object[][]{
                {"button","testng"},
                {"button","staircase"}
        };
    }

    @DataProvider(name = "submit")
    public static Object[][] submit() {
        return new Object[][]{
                {"submit","testng"},
                {"submit","staircase"}
        };
    }

    @DataProvider(name = "enter")
    public static Object[][] enter() {
        return new Object[][]{
                {"enter","testng"},
                {"enter","staircase"}
        };
    }

    //TODO 4 there is a less ugly way to do a product of 1D matrices wih daaProviders?
//    Object[][] matrix = new Object[searches.length + terms.length + 1][2];
//        for (int i = 0; i < matrix.length;) {
//        for (int j = 0; j < 2; j++) {
//            matrix[i][0] = searches[i/2];
//            matrix[i][1] = terms[j];
//            i++;
//        }
//    }
//        return matrix;
}

