package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.gtfs.feed.AgencyFeed;
import cf.charleseugeneloubao.gtfs.feed.RouteFeed;
import cf.charleseugeneloubao.gtfs.feed.StopFeed;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

public class AgencyDatabase {

    private AgencyFeed agencyMetadata;
    private LinkedHashMap<String,StopFeed> stops = new LinkedHashMap<>();
    private LinkedHashMap<String,Route> routes = new LinkedHashMap<>();
    private LinkedHashMap<String,Trip> trips = new LinkedHashMap<>();

    public AgencyDatabase(AgencyFeed agencyMetadata) {
        this.agencyMetadata = agencyMetadata;
    }

    public AgencyFeed getAgencyMetadata() {
        return agencyMetadata;
    }

    public void addTrip(RouteFeed routeFeed, Trip trip) {
        trips.put(trip.getMetadata().getTripId(),trip);
        if(!routes.containsKey(routeFeed.getRouteId())) {
            routes.put(routeFeed.getRouteId(),new Route(routeFeed));
        }
        routes.get(routeFeed.getRouteId()).addTrip(trip);
    }

    public void addStops(LinkedHashMap<String, StopFeed> stops) {
        this.stops.putAll(stops);
    }

    public Collection<Route> getRoutes() {
        return routes.values();
    }

    public Trip findTripById(String tripId) {
        return trips.get(tripId);
    }

    public StopFeed findStopById(String stopId) {
        return stops.get(stopId);
    }
}
