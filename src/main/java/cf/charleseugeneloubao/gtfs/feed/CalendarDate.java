package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class CalendarDate extends FeedObject{

public String getExceptionType(){
	return String.valueOf(get("exception_type").replace("null",""));
}

public String getServiceId(){
	return String.valueOf(get("service_id").replace("null",""));
}

public String getDate(){
	return String.valueOf(get("date").replace("null",""));
}

}