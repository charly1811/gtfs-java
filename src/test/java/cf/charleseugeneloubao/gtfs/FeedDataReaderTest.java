package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.collect.MultiMapLinkedHashMap;
import cf.charleseugeneloubao.gtfs.feed.*;
import org.junit.Test;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * Created by charl on 9/18/2015.
 */
public class FeedDataReaderTest {
    @Test
    public void testGetFeedObjects() throws IOException {

        File feedFolder = new File("dartfirststate_de_us");
        File agencyFile = new File(feedFolder, "agency.txt");
        File routesFile = new File(feedFolder, "routes.txt");
        File tripsFile = new File(feedFolder, "trips.txt");
        File stopsFile = new File(feedFolder, "stops.txt");
        File stopTimesFile = new File(feedFolder, "stop_times.txt");

        LinkedHashMap<String, TripDetails> tripDetailsMap = new LinkedHashMap<>();
        LinkedHashMap<String, TripFeed> tripFeedMap = new LinkedHashMap<>();
        LinkedHashMap<String, StopFeed> stopFeedMap = new LinkedHashMap<>();
        LinkedHashMap<String, RouteFeed> routeFeedMap = new LinkedHashMap<>();

        // Creates the agency feed from the file
        AgencyFeed agencyFeed = (AgencyFeed) FeedDataReader.getFeedObjects(AgencyFeed.class, agencyFile).toArray()[0];

        // Retrieve the list of stops from the file
        for (StopFeed stop : FeedDataReader.getFeedObjects(StopFeed.class, stopsFile)) {
            stopFeedMap.put(stop.getStopId(), stop);
        }

        // Retrieves the list of routes from the file and store them by id
        for (RouteFeed route : FeedDataReader.getFeedObjects(RouteFeed.class, routesFile)) {
            routeFeedMap.put(route.getRouteId(), route);
        }

        // Retrieve the list of trips from the file and store them by id
        for (TripFeed trip : FeedDataReader.getFeedObjects(TripFeed.class, tripsFile)) {
            tripDetailsMap.put(trip.getTripId(), new TripDetails(trip.getRouteId(), trip.getTripId()));
            tripFeedMap.put(trip.getTripId(), trip);
        }

        // Retrieves the list of stop times and adds them to the corresponding TripDetails instance
        for (StopTimeFeed stopTimeFeed : FeedDataReader.getFeedObjects(StopTimeFeed.class, stopTimesFile)) {
            tripDetailsMap.get(stopTimeFeed.getTripId()).addStopTime(stopTimeFeed);
        }

        // Map each Trip details to its RouteId;
        MultiMapLinkedHashMap<String,String> tripsDetailsByRouteMap = new MultiMapLinkedHashMap<>();
        for (TripDetails tripDetails: tripDetailsMap.values()) {
            tripsDetailsByRouteMap.put(tripDetails.getRouteId(),tripDetails.getTripId());
        }

        PrintWriter stringWriter = new PrintWriter(new StringWriter());
        FileWriter fileWriter;

        TripDetails details = tripDetailsMap.get(tripsDetailsByRouteMap.get("112").toArray()[1]);
        RouteFeed routeFeed = routeFeedMap.get(details.getRouteId());
        System.out.printf("%s  - %s\n",routeFeed.getRouteId(),routeFeed.getRouteLongName());
        for (int i =0; i < routeFeed.getRouteLongName().length()*2; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (StopTimeFeed feed : details.getStopTimes()) {
            System.out.printf("%s | %s\n", feed.getArrivalTime(), stopFeedMap.get(feed.getStopId()).getStopName());
        }
    }

}