package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class StopTimeFeed extends Feed {

    public String getStopId() {
        return String.valueOf(get("stop_id").replace("null", ""));
    }

    public String getStopSequence() {
        return String.valueOf(get("stop_sequence").replace("null", ""));
    }

    public String getTripId() {
        return String.valueOf(get("trip_id").replace("null", ""));
    }

    public String getArrivalTime() {
        return String.valueOf(get("arrival_time").replace("null", ""));
    }

    public String getDepartureTime() {
        return String.valueOf(get("departure_time").replace("null", ""));
    }

}