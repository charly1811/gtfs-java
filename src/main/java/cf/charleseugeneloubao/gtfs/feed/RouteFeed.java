package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class RouteFeed extends Feed {

public String getRouteType(){
	return String.valueOf(get("route_type").replace("null",""));
}

public String getRouteId(){
	return String.valueOf(get("route_id").replace("null",""));
}

public String getRouteLongName(){
	return String.valueOf(get("route_long_name").replace("null",""));
}

public String getRouteShortName(){
	return String.valueOf(get("route_short_name").replace("null",""));
}

}