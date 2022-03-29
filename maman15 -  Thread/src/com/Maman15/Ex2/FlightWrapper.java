package com.Maman15.Ex2;

public class FlightWrapper {


    private final int mNumberOfFlight;
    private final TypeOfRequest mType;


    public FlightWrapper(int numberOfFlight, TypeOfRequest type) {
        mNumberOfFlight = numberOfFlight;
        mType = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightWrapper that = (FlightWrapper) o;
        return mNumberOfFlight == that.mNumberOfFlight && mType == that.mType;
    }


}
