package com.Maman15.Ex2;

public class Track {

    private final int mId;
    private Integer mCurrentFlightNumber = null;

    public Track(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void lockTrack(int numberOfFlight) {
        mCurrentFlightNumber = numberOfFlight;
    }

    public void freeTrack() {
        mCurrentFlightNumber = null;
    }

    public boolean isFree() {
        return mCurrentFlightNumber == null;
    }
}
