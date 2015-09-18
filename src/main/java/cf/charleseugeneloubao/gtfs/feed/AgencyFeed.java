package cf.charleseugeneloubao.gtfs.feed;

import java.util.HashMap;

public class AgencyFeed extends Feed {

public String getAgencyId(){
	return String.valueOf(get("agency_id").replace("null",""));
}

public String getAgencyPhone(){
	return String.valueOf(get("agency_phone").replace("null",""));
}

public String getAgencyName(){
	return String.valueOf(get("agency_name").replace("null",""));
}

public String getAgencyUrl(){
	return String.valueOf(get("agency_url").replace("null",""));
}

public String getAgencyTimezone(){
	return String.valueOf(get("agency_timezone").replace("null",""));
}

}