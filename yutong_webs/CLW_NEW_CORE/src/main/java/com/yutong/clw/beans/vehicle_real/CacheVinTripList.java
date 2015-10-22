package com.yutong.clw.beans.vehicle_real;

import java.io.Serializable;
import java.util.List;

public class CacheVinTripList implements Serializable {
	 List<StuSiteNote> stuSiteNoteList;
	    
	    private String vehicle_vin;
	    
	    private String trip_id;

		public List<StuSiteNote> getStuSiteNoteList() {
			return stuSiteNoteList;
		}

		public void setStuSiteNoteList(List<StuSiteNote> stuSiteNoteList) {
			this.stuSiteNoteList = stuSiteNoteList;
		}

		public String getVehicle_vin() {
			return vehicle_vin;
		}

		public void setVehicle_vin(String vehicle_vin) {
			this.vehicle_vin = vehicle_vin;
		}

		public String getTrip_id() {
			return trip_id;
		}

		public void setTrip_id(String trip_id) {
			this.trip_id = trip_id;
		}
	    
	    
	    
}
