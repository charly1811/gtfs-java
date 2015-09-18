import java.util.HashMap;

public class TripFeed extends Feed {

public String getTripId(){
	return String.valueOf(get("trip_id").replace("null",""));
}

public String getShapeId(){
	return String.valueOf(get("shape_id").replace("null",""));
}

public String getRouteId(){
	return String.valueOf(get("route_id").replace("null",""));
}

public String getBlockId(){
	return String.valueOf(get("block_id").replace("null",""));
}

public String getServiceId(){
	return String.valueOf(get("service_id").replace("null",""));
}

}