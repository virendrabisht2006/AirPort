package com.crossover.trial.weather.domain;


/**
 * Basic airport information.
 *
 * @author code test administrator
 */
public class AirportData {

    /**
     * the three letter IATA code
     */
    private String iata;

    /*AtmosphericInformation for airport*/
    private AtmosphericInformation atmosphericInformation;

    /**
     * the four letter ICAO code
     */
    private String icao;

    /**
     * airport city
     */
    private String city;

    /**
     * airport country
     */
    private String country;

    /**
     * latitude value in degrees
     */
    private double latitude;

    /**
     * longitude value in degrees
     */
    private double longitude;

    /**
     * longitude value in feet
     */
    private double altitute;

    /* timezone from UTC */
    private double timezone;

    private char dst;

    private String description;

    public AirportData() {
    }


    public AirportData(String iataCode) {
        this.iata = iataCode;
    }

    public AtmosphericInformation getAtmosphericInformation() {
        return atmosphericInformation;
    }

    public void setAtmosphericInformation(AtmosphericInformation atmosphericInformation) {
        this.atmosphericInformation = atmosphericInformation;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "AirportData{" +
                "iata='" + iata + '\'' +
                ", atmosphericInformation=" + atmosphericInformation +
                ", icao='" + icao + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitute=" + altitute +
                ", timezone=" + timezone +
                ", dst=" + dst +
                ", description='" + description + '\'' +
                '}';
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getAltitute() {
        return altitute;
    }

    public void setAltitute(double altitute) {
        this.altitute = altitute;
    }

    public double getTimezone() {
        return timezone;
    }

    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    public char getDst() {
        return dst;
    }

    public void setDst(char dst) {
        this.dst = dst;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirportData that = (AirportData) o;

        return iata.equals(that.iata);

    }

    @Override
    public int hashCode() {
        return iata.hashCode();
    }

}
