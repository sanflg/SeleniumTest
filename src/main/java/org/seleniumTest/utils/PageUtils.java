package org.seleniumTest.utils;

import java.net.URL;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.Level;

/**
 * Added utilities for PageObjectModel pages manipulation and checks.
 */
public class PageUtils {
    private static final AllureLogger LOGGER = new AllureLogger(PageUtils.class);

    /**
     * Build a Map<String,String> from the query of an url
     *
     * @param url URL used to construct the query map with url.getQuery().
     * @return Map of key-values using the query from the url.
     */
    public static Map<String, String> getQueryParamsMap(URL url) {
        LOGGER.log(Level.INFO,"Building parameters map from url: " + url.toString());

        Map<String, String> map = new LinkedHashMap<>();

        Arrays.stream(url.getQuery().split("&")).forEach(param -> {
            String[] slicedParam = param.split("=");
            map.put(slicedParam[0], slicedParam[1]);
        });

        return map;
    }

    /**
     * Slice url query to get the parameters list to verify that every "contained" parameter is present
     * in target "container" URL.
     *
     * @param contained URL base to check that query params are present in target "container" baseline.
     * @param container URL target to contrast "contained" query params.
     * @return boolean of 1-1 checks for "contained" query params presence in "container" target.
     * @see #getQueryParamsMap(URL)
     */
    public static boolean queryParamsPresent(URL contained, URL container) {
        LOGGER.log(Level.INFO, String.format("Started comparison of query params: \n\t<%s>\n\t<%s>",
                contained.toString(),
                container.toString()));

        Map<String, String> containerMap = getQueryParamsMap(container);
        AtomicBoolean comparisonFlag = new AtomicBoolean(true);

        getQueryParamsMap(contained).forEach((key, value) -> {
            if (!containerMap.get(key).equals(value)) {
                comparisonFlag.set(false);
                LOGGER.log(Level.ERROR, "Url params are not contained on target url");
            }
        });

        return comparisonFlag.getPlain();
    }
}
