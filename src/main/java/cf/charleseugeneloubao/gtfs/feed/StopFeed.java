package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class StopFeed extends Feed {

public String getStopId(){
	return String.valueOf(get("stop_id").replace("null",""));
}

public String getStopCode(){
	return String.valueOf(get("stop_code").replace("null",""));
}

public String getStopLon(){
	return String.valueOf(get("stop_lon").replace("null",""));
}

public String getStopName(){
	return String.valueOf(get("stop_name").replace("null",""));
}

public String getStopLat(){
	return String.valueOf(get("stop_lat").replace("null",""));
}

}