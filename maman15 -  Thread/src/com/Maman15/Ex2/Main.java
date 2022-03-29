package com.Maman15.Ex2;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int NUMBER_OF_FLIGHTS = 10;

    public static void main(String[] args) throws InterruptedException {
        AirPort benGurionAirport = new AirPort("TLV", 3);
        AirPort sydneyAirport = new AirPort("SYD", 3);
        ArrayList<Flight> flights = generatorRandomFlights(benGurionAirport, sydneyAirport);
        for (int i = 0; i < NUMBER_OF_FLIGHTS; i++) {
            flights.get(i).start();
        }
        for (int i = 0; i < NUMBER_OF_FLIGHTS; i++) {
            flights.get(i).join();
        }
    }

    private static ArrayList<Flight> generatorRandomFlights(AirPort land, AirPort depart) {
        Random random = new Random();
        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_FLIGHTS; i++) {
            if (random.nextBoolean()) {
                flights.add(new Flight(100 + i, land, depart));
            } else {
                flights.add(new Flight(100 + i, depart, land));
            }

        }
        return flights;
    }


}

