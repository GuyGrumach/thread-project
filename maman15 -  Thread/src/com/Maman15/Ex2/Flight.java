package com.Maman15.Ex2;

public class Flight extends Thread {

    private final int mFlightId;
    private final AirPort mDepartAirport, mLandAirport;

    public Flight(int flightId, AirPort departAirport, AirPort landAirport) {
        mFlightId = flightId;
        mDepartAirport = departAirport;
        mLandAirport = landAirport;
    }

    @Override
    public void run() {
        int trackDepart = mDepartAirport.depart(mFlightId);
        simulateFlight(500);
        mDepartAirport.freeRunWay(trackDepart);
        simulateFlight(2000);
        int trackLand = mLandAirport.land(mFlightId);
        simulateFlight(500);
        mLandAirport.freeRunWay(trackLand);
        System.out.printf("Flight %d - FINISHED%n", mFlightId);
    }

    private void simulateFlight(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
