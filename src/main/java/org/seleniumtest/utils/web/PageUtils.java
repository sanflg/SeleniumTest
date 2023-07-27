package org.seleniumtest.utils.web;

import java.net.URL;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Added utilities for PageObjectModel pages manipulation and checks.
 */
public class PageUtils {
    private static final Logger LOGGER = LogManager.getLogger(PageUtils.class);

    private PageUtils(){}

    /**
     * Build a Map<String,String> from the query of an url
     *
     * @param url URL used to construct the query map with url.getQuery().
     * @return Map of key-values using the query from the url.
     */
    public static Map<String, String> getQueryParamsMap(URL url) {
        LOGGER.info("Building parameters map from url: {}", url);

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
        LOGGER.info("Started comparison of query params: \n\t<{}>\n\t<{}>", contained, container);

        Map<String, String> containerMap = getQueryParamsMap(container);
        AtomicBoolean comparisonFlag = new AtomicBoolean(true);

        getQueryParamsMap(contained).forEach((key, value) -> {
            if (!containerMap.get(key).equals(value)) {
                comparisonFlag.set(false);
                LOGGER.error("Url params are not contained on target url");
            }
        });

        return comparisonFlag.getPlain();
    }
}
