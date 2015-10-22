package com.yutong.clw.beans.parents;

import java.util.Date;
import java.util.List;

public class VehicleStationsPerDayUpOrDown
{
		private String vin;
		private Date date;
		private List<Station> stations = null;
		private Station school = null;
		
		public String getVin()
		{
			return this.vin;
		}
		
		public void setVin(String vin)
		{
			this.vin = vin;
		}
		
		public Date getDate()
		{
			return this.date;
		}
		
		public void setDate(Date date)
		{
			this.date = date;
		}
		
		public List<Station> getStations()
		{
			return this.stations;
		}
		
		public List<Station> setStations(List<Station> stations)
		{
			return this.stations = stations;
		}
		
		public Station getSchool()
		{
			return this.school;
		}
		
		public Station setSchool(Station station)
		{
			return this.school = station;
		}
}
