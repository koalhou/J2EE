package com.neusoft.clw.sysmanage.vacation.service;

import java.util.Calendar;

import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.sysmanage.vacation.domain.Vacation;

public class VacationServiceImpl extends ServiceImpl implements VacationService{

	public String saveVacation(String orgsl,String[] days,int year,String month,String uid) throws DataAccessIntegrityViolationException, DataAccessException {

		if (days != null&&days.length>0) {
			String[] orgs = orgsl.split(",");
			for (String org : orgs) {
				Vacation va = new Vacation();
				va.setOrgID(org);
				va.setYear(year);
				va.setMonth(month);
				va.setUpdatedBy(uid);
				dao.update("vacation.del", va);

				for (String day : days) {
					Vacation v = new Vacation();
					v.setOrgID(org);
					v.setYear(year);
					v.setMonth(month);
					Calendar cal = Calendar.getInstance();
					cal.set(year, Integer.parseInt(month)-1, Integer.parseInt(day));
					v.setDay(cal.getTime());
					v.setCreatedBy(uid);
					dao.insert("vacation.add", v);
				}
			}
		}
		return null;
	}

}
