package com.hook38.sporttimer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hook38.sporttimer.model.CountdownTimerModel;
import com.hook38.sporttimer.model.sql.DaoMaster;
import com.hook38.sporttimer.model.sql.DaoMaster.DevOpenHelper;
import com.hook38.sporttimer.model.sql.DaoSession;
import com.hook38.sporttimer.model.sql.RoutineSQL;
import com.hook38.sporttimer.model.sql.TimeUnitSQL;
import com.hook38.sporttimer.model.sql.RoutineSQLDao;
import com.hook38.sporttimer.model.sql.TimeUnitSQLDao;
import com.hook38.sporttimer.utils.TimeUnits;

import de.greenrobot.dao.QueryBuilder;


public class CountdownTimerStoreController {
	private SQLiteDatabase db;
	private DaoMaster daoMaster;
    private DaoSession daoSession;
    private RoutineSQLDao routineDao;
    private TimeUnitSQLDao unitDao;
    
	public CountdownTimerStoreController(Context context) {
		DevOpenHelper helper = 
				new DaoMaster.DevOpenHelper(context, 
						"sporttimer-countdown-routine", 
						null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		unitDao = daoSession.getTimeUnitSQLDao();
		routineDao = daoSession.getRoutineSQLDao();
	}
	
	public void close() {
		db.close();
	}
	
	/**
	 * Store the count down timer routine (CountdownTimerModel) with the given 
	 * routine name.
	 * @param model A given routine count down timer model.
	 * @param routineName Name of the routine.
	 */
	public void storeTimerModel(CountdownTimerModel model, String routineName) {	
		long routineId = this.storeRoutine(routineName);
		this.deleteTimeUnitsByRoutineId(routineId);

		for(int i=0; i<model.size(); i++){
			this.storeTimeUnit(model.get(i).toString(), i, routineId);
		}
	}
	
	private void deleteTimeUnitsByRoutineId(long id) {
		List<TimeUnitSQL> units = this.retrieveStoredTimeUnits(id);
		for(TimeUnitSQL unit:units) {
			unitDao.delete(unit);
		}
	}
	
	public void editRoutine(String oldName, String newName) {
		RoutineSQL routine = this.retrieveStoredRoutine(oldName);
		routine.setName(newName);
		routineDao.update(routine);
				
	}
	
	public void deleteRoutine(String routineName) {
		RoutineSQL routine = this.retrieveStoredRoutine(routineName);
		this.deleteTimeUnitsByRoutineId(routine.getId());
		daoSession.delete(routine);
	}
	
	public CountdownTimerModel retrieveTimerModel(String routineName) {
		RoutineSQL storedRoutine = this.retrieveStoredRoutine(routineName);
		
		CountdownTimerModel model = new CountdownTimerModel();
		if(storedRoutine != null) {
			List<TimeUnitSQL> storedUnits = this.retrieveStoredTimeUnits(storedRoutine.getId());
			for(TimeUnitSQL unit: storedUnits) {
				model.add(new TimeUnits(unit.getTimeunit()));
			}
		}
		return model;		
	}
	
	/**
	 * Store the routine given the name of the routine, and return the store ID of
	 * the routine. If the given name already exist, it return the ID of the 
	 * stored routine.
	 * @param routineName name of the routine.
	 * @return ID of the routine, given the name.
	 */
	public long storeRoutine(String routineName) throws ClassCastException {

		RoutineSQL storedRoutine = this.retrieveStoredRoutine(routineName);
		if(storedRoutine == null) {
			//No routine with the given name, store the routine.
			RoutineSQL routine = new RoutineSQL(null, routineName);
			routineDao.insert(routine);
			return routine.getId();
		}

		return storedRoutine.getId();

	}
	
	private long storeTimeUnit(String unitString, int order, long routineId) {
		TimeUnitSQL storedTimeUnit = this.retrieveStoredTimeUnits(routineId, order);
		if(storedTimeUnit == null) {
			
			//No time units with the given routine Id at that position had been stored
			//Store a new one
			TimeUnitSQL unit = new TimeUnitSQL(null, unitString, order, routineId);
			unitDao.insert(unit);
			return unit.getId();
		}else{
			//Time time units with the given routine Id at that position had been stored
			//Update to the new one
			storedTimeUnit.setTimeunit(unitString);
			unitDao.update(storedTimeUnit);
			return storedTimeUnit.getId();
		}
	}
	
	
	private List<TimeUnitSQL> retrieveStoredTimeUnits(long routineId) {
		return unitDao.queryBuilder()
				.where(com.hook38.sporttimer.model.sql
						.TimeUnitSQLDao.Properties.RoutineId
						.eq(routineId))						
				.orderAsc(com.hook38.sporttimer.model.sql
						.TimeUnitSQLDao.Properties.Orders)
				.list();
	}
	
	private TimeUnitSQL retrieveStoredTimeUnits(long routineId, int order) {
		QueryBuilder<TimeUnitSQL> qb = unitDao.queryBuilder();
		qb.where(com.hook38.sporttimer.model.sql
						.TimeUnitSQLDao.Properties.RoutineId
						.eq(routineId),
				com.hook38.sporttimer.model.sql
						.TimeUnitSQLDao.Properties.Orders
						.eq(order));
		return qb.unique();
	}
	
	/**
	 * Return a list of stored routine given the routine name. (The design 
	 * should only have one routine with a given name).
	 * @param routineName
	 * @return
	 */
	private RoutineSQL retrieveStoredRoutine (String routineName) { 
		return routineDao.queryBuilder()
			.where(com.hook38.sporttimer.model.sql
					.RoutineSQLDao.Properties.Name
					.eq(routineName))
			.unique();
		
	}
	
	/**
	 * Return a list of all stored routine in the database.
	 * @return a list of all stored routine.
	 */
	private List<RoutineSQL> retrieveStoredRoutines () { 
		return routineDao.queryBuilder()
			.orderAsc(com.hook38.sporttimer.model.sql
					.RoutineSQLDao.Properties.Name)
			.list();
	}
	
	/**
	 * An access point to routines.
	 * @return A list of routines
	 */
	public List<String> getRoutines() {
		List<RoutineSQL> sqlList = this.retrieveStoredRoutines();
		List<String> list = new ArrayList<String>();
		for(RoutineSQL routine:sqlList) {
			list.add(routine.getName());
		}
		return list;
	}
}
