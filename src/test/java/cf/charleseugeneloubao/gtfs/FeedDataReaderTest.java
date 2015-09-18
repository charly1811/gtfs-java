package cf.charleseugeneloubao.gtfs;

import cf.charleseugeneloubao.gtfs.feed.Route;
import cf.charleseugeneloubao.gtfs.feed.Stop;
import cf.charleseugeneloubao.gtfs.feed.StopTime;
import cf.charleseugeneloubao.gtfs.feed.Trip;
import org.junit.Test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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

        LinkedHashMap<String, Stop> stops = new LinkedHashMap<>();

        for (Stop stop : FeedDataReader.getFeedObjects(Stop.class, stopsFile)) {
            stops.put(stop.getStopId(), stop);
        }

        LinkedHashMap<String, Route> routes = new LinkedHashMap<>();

        for (Route route : FeedDataReader.getFeedObjects(Route.class, routesFile)) {
            routes.put(route.getRouteId(), route);
        }

        LinkedHashMap<String, Trip> trips = new LinkedHashMap<>();

        for (Trip trip : FeedDataReader.getFeedObjects(Trip.class, tripsFile)) {
            trips.put(trip.getTripId(), trip);
        }

        LinkedHashMap<String, LinkedHashSet<StopTime>> stopTimes = new LinkedHashMap<>();
        for (StopTime stopTime : FeedDataReader.getFeedObjects(StopTime.class, stopTimesFile)) {
            if (!trips.containsKey(stopTime.getTripId()))
                continue;

            String routeId = trips.get(stopTime.getTripId()).getRouteId();
            if (!stopTimes.containsKey(routeId)) {
                stopTimes.put(routeId, new LinkedHashSet<StopTime>());
            }
            stopTimes.get(routeId).add(stopTime);
        }

        FileWriter fileWriter;

        File scheduleFolder = new File("dart_first_state");
        scheduleFolder.mkdir();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter,true);

        for (String key : stopTimes.keySet()) {
            Route route = routes.get(key);
            System.out.printf("Writing schedule for route %s\n",route.getRouteId());
            printWriter.printf("(Route %s) %s\n", route.getRouteId(), route.getRouteLongName());
            for (StopTime stopTime : stopTimes.get(key)) {
                Stop stop = stops.get(stopTime.getStopId());
                printWriter.printf("%s : %s\n", stopTime.getArrivalTime(), stop.getStopName());
            }

            File file = new File(scheduleFolder,"route_"+route.getRouteId()+".txt");
            file.createNewFile();
            fileWriter = new FileWriter(file,true);
            fileWriter.write(stringWriter.toString());
            stringWriter.getBuffer().setLength(0);
        }
    }

}