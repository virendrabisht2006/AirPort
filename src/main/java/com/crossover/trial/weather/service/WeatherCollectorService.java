package com.crossover.trial.weather.service;

import com.crossover.trial.weather.util.WeatherDataHolder;
import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.domain.AirportData;
import com.crossover.trial.weather.domain.AtmosphericInformation;
import com.crossover.trial.weather.domain.DataPoint;
import com.crossover.trial.weather.domain.DataPointType;
import com.crossover.trial.weather.util.FileHelper;

/**
 * Created by esha on 12/09/16.
 */
public class WeatherCollectorService {

    //
    // Internal support methods
    //

    /**
     * Update the airports weather data with the collected data.
     *
     * @param iataCode  the 3 letter IATA code
     * @param pointType the point type {@link DataPointType}
     * @param dp        a datapoint object holding pointType data
     * @throws WeatherException if the update can not be completed
     */
    public void addDataPoint(String iataCode, String pointType, DataPoint dp) throws WeatherException {
        AirportData ad = RestWeatherQueryEndpoint.getAirportData(iataCode);
        AtmosphericInformation ai = ad.getAtmosphericInformation();
        updateAtmosphericInformation(ai, pointType, dp);
    }

    /**
     * update atmospheric information with the given data point for the given point type
     *
     * @param ai        the atmospheric information object to update
     * @param pointType the data point type as a string
     * @param dp        the actual data point
     */
    public void updateAtmosphericInformation(AtmosphericInformation ai, String pointType, DataPoint dp) throws WeatherException {
        if (pointType.equalsIgnoreCase(DataPointType.WIND.name())) {
            if (dp.getMean() >= 0) {
                ai.setWind(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        if (pointType.equalsIgnoreCase(DataPointType.TEMPERATURE.name())) {
            if (dp.getMean() >= -50 && dp.getMean() < 100) {
                ai.setTemperature(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        if (pointType.equalsIgnoreCase(DataPointType.HUMIDTY.name())) {
            if (dp.getMean() >= 0 && dp.getMean() < 100) {
                ai.setHumidity(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        if (pointType.equalsIgnoreCase(DataPointType.PRESSURE.name())) {
            if (dp.getMean() >= 650 && dp.getMean() < 800) {
                ai.setPressure(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        if (pointType.equalsIgnoreCase(DataPointType.CLOUDCOVER.name())) {
            if (dp.getMean() >= 0 && dp.getMean() < 100) {
                ai.setCloudCover(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        if (pointType.equalsIgnoreCase(DataPointType.PRECIPITATION.name())) {
            if (dp.getMean() >= 0 && dp.getMean() < 100) {
                ai.setPrecipitation(dp);
                ai.setLastUpdateTime(System.currentTimeMillis());
                return;
            }
        }

        throw new IllegalStateException("couldn't update atmospheric data");
    }

    /**
     * Add a new known airport to our list.
     *
     * @param iataCode  3 letter code
     * @param latitude  in degrees
     * @param longitude in degrees
     * @return the added airport
     */
    public boolean addAirport(String iataCode, double latitude, double longitude) {
        boolean isAdded = false;
        AirportData ad = new AirportData(iataCode);

        AtmosphericInformation ai = addAtmoshphericInformartion();
        ad.setAtmosphericInformation(ai);
        ad.setLatitude(latitude);
        ad.setLatitude(longitude);

        if (!WeatherDataHolder.airportData.containsKey(ad.getIata())) {
            isAdded = true;
            WeatherDataHolder.airportData.put(ad.getIata(), ad);
            FileHelper.writeAirportInFile(ad);
        }

        return isAdded;
    }

    public boolean removeAirport(String iata) {
        boolean isDeleted = false;
        if (WeatherDataHolder.airportData.containsKey(iata)) {
            WeatherDataHolder.airportData.remove(iata);
            FileHelper.removeAirportFromFile();
            isDeleted = true;
        }
        return true;
    }

    public AtmosphericInformation addAtmoshphericInformartion() {
        DataPoint temprature = new DataPoint(1, 2, 3, 4, 5);
        DataPoint wind = new DataPoint(1, 2, 3, 4, 5);
        DataPoint humidity = new DataPoint(1, 2, 3, 4, 5);
        DataPoint cloudCover = new DataPoint(1, 2, 3, 4, 5);
        DataPoint percipitation = new DataPoint(1, 2, 3, 4, 5);
        DataPoint pressure = new DataPoint(1, 2, 3, 4, 5);
        return new AtmosphericInformation(temprature, wind, humidity, percipitation, pressure, cloudCover);
    }
}
