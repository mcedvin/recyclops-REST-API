package com.recycling.Test.Service;

import com.recycling.Test.Dao.StationSQLDao;
import com.recycling.recycling.production.Position;
import com.recycling.recycling.production.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StationService {

    @Autowired
    private StationSQLDao stationDao;

    public Collection<Station> getAllStations() {
        return stationDao.getAllStations();
    }
    public void addStation(Station s){
        stationDao.addStation(s);
    }
    public Station getStationFromPosition(Position p){
        return stationDao.getStationFromPosition(p);
    }
}
