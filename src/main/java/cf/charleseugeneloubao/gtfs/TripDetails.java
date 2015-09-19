package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.gtfs.feed.StopTimeFeed;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Contains all stop times associated with a trip
 */
public class TripDetails {
    private String routeId;
    private String tripId;
    private LinkedHashSet<StopTimeFeed> stopTimes;

    public TripDetails(String routeId, String tripId) {
        this.routeId = routeId;
        this.tripId = tripId;
        this.stopTimes = new LinkedHashSet<>();
    }

    /**
     * @return The route Id associated to this route
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     * @return The trip id associated to this route
     */
    public String getTripId() {
        return tripId;
    }

    public LinkedHashSet<StopTimeFeed> getStopTimes() {
        return stopTimes;
    }

    public void addStopTime(StopTimeFeed stopTimeFeed) {
        stopTimes.add(stopTimeFeed);
    }
}
