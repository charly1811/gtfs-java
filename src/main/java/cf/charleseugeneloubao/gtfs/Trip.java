package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.gtfs.feed.StopTimeFeed;
import cf.charleseugeneloubao.gtfs.feed.TripFeed;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Represents a trip
 */
public class Trip {

    private LinkedHashSet<StopTimeFeed> stopTimes;
    private TripFeed metadata;

    public Trip(LinkedHashSet<StopTimeFeed> stopTimes, TripFeed metadata) {
        this.stopTimes = stopTimes;
        this.metadata = metadata;
    }

    public LinkedHashSet<StopTimeFeed> getStopTimes() {
        return stopTimes;
    }

    public TripFeed getMetadata() {
        return metadata;
    }
}
