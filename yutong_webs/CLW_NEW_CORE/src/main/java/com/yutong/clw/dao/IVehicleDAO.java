package com.yutong.clw.dao;

import java.util.List;

import com.yutong.clw.beans.cl.VehicleBean;

public interface IVehicleDAO {
	List<VehicleBean> getVehicleParam();
	List<VehicleBean> getAllVehicleParam();
}
