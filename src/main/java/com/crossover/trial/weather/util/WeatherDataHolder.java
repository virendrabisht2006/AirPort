package com.crossover.trial.weather.util;

import com.crossover.trial.weather.domain.AirportData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by esha on 10/09/16.
 */
public class WeatherDataHolder {
    /**
     * all known airports
     */

    public static int index = 1;

    public static Map<String, AirportData> airportData = new ConcurrentHashMap<>();
    /**
     * Internal performance counter to better understand most requested information, this map can be improved but
     * for now provides the basis for future performance optimizations. Due to the stateless deployment architecture
     * we don't want to write this to disk, but will pull it off using a REST request and aggregate with other
     * performance metrics {@link #ping()}
     */
    public static Map<AirportData, Integer> requestFrequency = new ConcurrentHashMap<>();

    public static Map<Double, Integer> radiusFreq = new ConcurrentHashMap<>();
}
