package com.neusoft.clw.vncs.dao;

import java.util.List;

import com.neusoft.clw.vncs.domain.VehicleBean;

public interface IVehicleDAO {
	List<VehicleBean> getVehicleParam();
	List<VehicleBean> getAllVehicleParam();
}
