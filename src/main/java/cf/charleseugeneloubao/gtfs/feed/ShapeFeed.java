package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class ShapeFeed extends Feed {

public String getShapePtSequence(){
	return String.valueOf(get("shape_pt_sequence").replace("null",""));
}

public String getShapeId(){
	return String.valueOf(get("shape_id").replace("null",""));
}

public String getShapePtLat(){
	return String.valueOf(get("shape_pt_lat").replace("null",""));
}

public String getShapePtLon(){
	return String.valueOf(get("shape_pt_lon").replace("null",""));
}

}