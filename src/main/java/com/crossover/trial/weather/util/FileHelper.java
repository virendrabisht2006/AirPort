package com.crossover.trial.weather.util;

import com.crossover.trial.weather.domain.AirportData;

import java.io.*;


public class FileHelper {
    public static final String FILE_DELIMETER = ",";
    private static final String FILE_PATH ="src/main/resources/airports.dat";
    /*
    when new airport added, Write airport into a file asynchronously.
     */
    public static void writeAirportInFile(AirportData airportData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File airportDataFile = new File(FILE_PATH);
                BufferedWriter bufferedWriter =null;
                int index = WeatherDataHolder.index;
                index = index+1;

                try {
                    synchronized (airportDataFile) {

                        if (!airportDataFile.exists()) {
                            airportDataFile.createNewFile();
                        }

                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(airportDataFile,true)));

                        bufferedWriter.write(createAirportString(airportData,index));
                    }

                } catch (IOException e) {
                    System.out.println("Exception occured while writing into file.." + e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static void removeAirportFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File airportDataFile = new File(FILE_PATH);
                BufferedWriter bufferedWriter = null;
                int index =1;
                try {
                    StringBuilder stringBuilder = new StringBuilder();

                    for(AirportData airportData: WeatherDataHolder.airportData.values()) {
                        stringBuilder.append(createAirportString(airportData, index));
                        index = index + 1;

                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(airportDataFile)));
                        bufferedWriter.write(stringBuilder.toString());
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error occured shile writing file: "+e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Error occured shile writing file: "+e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

    }

    public static String createAirportString(AirportData airportData, int index){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(index).append(FILE_DELIMETER)
                .append(airportData.getDescription()).append(FILE_DELIMETER)
                .append(airportData.getCity()).append(FILE_DELIMETER)
                .append(airportData.getCountry()).append(FILE_DELIMETER)
                .append(airportData.getIata()).append(FILE_DELIMETER)
                .append(airportData.getIcao()).append(FILE_DELIMETER)
                .append(airportData.getLatitude()).append(FILE_DELIMETER)
                .append(airportData.getLongitude()).append(FILE_DELIMETER)
                .append(airportData.getAltitute()).append(FILE_DELIMETER)
                .append(airportData.getTimezone()).append(FILE_DELIMETER)
                .append(airportData.getDst()).append("\r\n");
        return stringBuilder.toString();
    }

    public static void uploadDataFromFile(String fileName) throws IOException{
        if(fileName == null || fileName.isEmpty()){
            fileName = FILE_PATH;
        }
        File airportDataFile = new File(fileName);
        if (!airportDataFile.exists() || airportDataFile.length() == 0) {
            System.err.println(airportDataFile + " is not a valid file");
            System.exit(1);
        }

        AirportLoader al = new AirportLoader();
        al.upload(new FileInputStream(airportDataFile));

    }
}
