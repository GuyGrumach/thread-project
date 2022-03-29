package com.Maman15.Ex2;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AirPort {
    private final String mName;
    private final HashMap<Integer, Track> mTracks = new HashMap<>();
    private final Object mTrackingLock = new Object();

    private final BlockingQueue<FlightWrapper> mFlights = new LinkedBlockingQueue<>();

    public AirPort(String name, int numberOfTracks) {
        mName = name;
        for (int i = 0; i < numberOfTracks; i++) {
            mTracks.put(i, new Track(i));
        }
    }

    public synchronized int depart(int flightId) {
        System.out.printf("{%s} - Flight - {%d} requested to depart\n", mName, flightId);
        FlightWrapper flightDepart = new FlightWrapper(flightId, TypeOfRequest.Depart);
        mFlights.offer(new FlightWrapper(flightId, TypeOfRequest.Depart));
        Track track;
        do {
            track = getFreeTrack();
            if (track != null && mFlights.peek().equals(flightDepart)) {
                mFlights.poll();
                synchronized (mTrackingLock) {
                    track.lockTrack(flightId);
                }
                System.out.printf("{%s} - Flight >>>>>>>>- {%d} departing from track - {%d}\n", mName, flightId, track.getId());
                return track.getId();
            }
            waitUntilHasFreeTrack();
        } while (track == null || !mFlights.peek().equals(flightDepart));
        return track.getId();
    }

    private void waitUntilHasFreeTrack() {
        try {
            wait();
        } catch (Exception ignored) {

        }
    }

    public synchronized int land(int flightId) {
        FlightWrapper flightLand = new FlightWrapper(flightId, TypeOfRequest.Land);
        mFlights.offer(flightLand);
        System.out.printf("{%s} - Flight - {%d} requested to land\n", mName, flightId);
        Track track;
        do {
            track = getFreeTrack();
            if (track != null && mFlights.peek().equals(flightLand)) {
                mFlights.poll();
                synchronized (mTrackingLock) {
                    track.lockTrack(flightId);
                }
                System.out.printf("{%s} - Flight <<<<<<<- {%d} landing from track - {%d}\n", mName, flightId, track.getId());
                return track.getId();
            }
            waitUntilHasFreeTrack();
        } while (track == null || !mFlights.peek().equals(flightLand));
        return track.getId();
    }

    public synchronized void freeRunWay(int track) {
        synchronized (mTrackingLock) {
            mTracks.get(track).freeTrack();
        }
        System.out.printf("%s - Track number " + track + "Free%n", mName, track);
        try {
            notify();
        } catch (Exception ignored) {
        }

    }

    private synchronized Track getFreeTrack() {
        for (Integer trackId : mTracks.keySet()) {
            Track track = mTracks.get(trackId);
            if (track.isFree()) {
                return track;
            }
        }
        return null;
    }


}
