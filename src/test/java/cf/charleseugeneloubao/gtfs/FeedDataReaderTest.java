package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.gtfs.feed.*;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;

import static org.junit.Assert.*;

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
        File shapesFile = new File(feedFolder, "shapes.txt");
        File stopTimesFile = new File(feedFolder, "stop_times.txt");

        AgencyDatabase database = new AgencyDatabase((AgencyFeed) FeedDataReader.getFeedObjects(AgencyFeed.class,agencyFile).toArray()[0]);

        // Retrieve the list of stops from the file
        LinkedHashMap<String, StopFeed> stops = new LinkedHashMap<>();
        for (StopFeed stop : FeedDataReader.getFeedObjects(StopFeed.class, stopsFile)) {
            stops.put(stop.getStopId(), stop);
        }

        // Adds all stops to the database
        database.addStops(stops);


        // Retrieves the list of routes from the file and store them by id
        LinkedHashMap<String, RouteFeed> routes = new LinkedHashMap<>();
        for (RouteFeed route : FeedDataReader.getFeedObjects(RouteFeed.class, routesFile)) {
            routes.put(route.getRouteId(), route);
        }

        // Retrieve the list of trips from the file and store them by id
        LinkedHashMap<String, TripFeed> trips = new LinkedHashMap<>();
        for (TripFeed trip : FeedDataReader.getFeedObjects(TripFeed.class, tripsFile)) {
            trips.put(trip.getTripId(), trip);
        }

        //Retrieves the stops from the file
        // Each stops belongs to a trip so stops are stored by trip id
        LinkedHashMap<String, LinkedHashSet<StopTimeFeed>> stopTimes = new LinkedHashMap<>();
        for (StopTimeFeed stopTime : FeedDataReader.getFeedObjects(StopTimeFeed.class, stopTimesFile)) {
            if (!trips.containsKey(stopTime.getTripId()))
                continue;

            if (!stopTimes.containsKey(stopTime.getTripId())) {
                stopTimes.put(stopTime.getTripId(), new LinkedHashSet<StopTimeFeed>());
            }
            stopTimes.get(stopTime.getTripId()).add(stopTime);
        }


        // Add each trips to the database
        for (TripFeed tripMetadata : trips.values()) {
            RouteFeed routeMetaData = routes.get(tripMetadata.getRouteId());
            LinkedHashSet<StopTimeFeed> busStops = stopTimes.get(tripMetadata.getTripId());
            Trip trip = new Trip(busStops, tripMetadata);
            database.addTrip(routeMetaData, trip);
        }

        // Let's try to read this database and write each routes to a particular file
        File scheduleFolder = new File("schedule");
        scheduleFolder.mkdir();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter;

        for (Route route : database.getRoutes()) {
            File routeFile = new File(scheduleFolder,String.format("route_%s.txt",route.getMetaData().getRouteId()));
            routeFile.createNewFile();

            printWriter = new PrintWriter(stringWriter);
            printWriter.printf("%s - %s\n",route.getMetaData().getRouteId(),route.getMetaData().getRouteLongName());
            for (int i = 0; i < route.getMetaData().getRouteLongName().length()*2; i++) {
                printWriter.print("*");
            }
            printWriter.println();
            printWriter.println();

            for (String tripId : route.getTrips()) {
                Trip trip = database.findTripById(tripId);
                for (StopTimeFeed stopTimeFeed : trip.getStopTimes()) {
                    printWriter.printf("%s | %s\n",stopTimeFeed.getArrivalTime(),database.findStopById(stopTimeFeed.getStopId()).getStopName());
                }
                printWriter.println();
            }
            String content = stringWriter.toString();
            stringWriter.getBuffer().setLength(0);
            printWriter = new PrintWriter(routeFile);
            printWriter.println(content);
            printWriter.flush();
        }

    }
}