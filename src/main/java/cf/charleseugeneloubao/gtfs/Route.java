package cf.charleseugeneloubao.gtfs;


import cf.charleseugeneloubao.gtfs.feed.RouteFeed;
import cf.charleseugeneloubao.gtfs.feed.StopTimeFeed;
import cf.charleseugeneloubao.gtfs.feed.TripFeed;

import java.util.*;

/**
 * A represents a route. a route is a collection of {@link Trip}
 */

public class Route {

    private LinkedHashSet<String> trips;
    private RouteFeed metaData;

    public Route(LinkedHashSet<String> trips, RouteFeed data) {
        this.trips = trips;
        this.metaData = data;
    }

    public Route(RouteFeed route) {
        this(new LinkedHashSet<String>(), route);
    }

    public void addTrip(Trip trip) {
        trips.add(trip.getMetadata().getTripId());
    }

    public RouteFeed getMetaData() {
        return metaData;
    }


    public Collection<String> getTrips() {
        return trips;
    }


}
