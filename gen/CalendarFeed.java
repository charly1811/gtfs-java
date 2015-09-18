import java.util.HashMap;

public class CalendarFeed extends Feed {

public String getSaturday(){
	return String.valueOf(get("saturday").replace("null",""));
}

public String getThursday(){
	return String.valueOf(get("thursday").replace("null",""));
}

public String getMonday(){
	return String.valueOf(get("monday").replace("null",""));
}

public String getSunday(){
	return String.valueOf(get("sunday").replace("null",""));
}

public String getStartDate(){
	return String.valueOf(get("start_date").replace("null",""));
}

public String getFriday(){
	return String.valueOf(get("friday").replace("null",""));
}

public String getTuesday(){
	return String.valueOf(get("tuesday").replace("null",""));
}

public String getWednesday(){
	return String.valueOf(get("wednesday").replace("null",""));
}

public String getServiceId(){
	return String.valueOf(get("service_id").replace("null",""));
}

public String getEndDate(){
	return String.valueOf(get("end_date").replace("null",""));
}

}