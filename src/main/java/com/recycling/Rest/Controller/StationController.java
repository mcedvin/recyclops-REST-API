package com.recycling.Rest.Controller;


import com.recycling.Rest.Dao.CleaningScheduleSQLDao;
import com.recycling.Rest.Dao.MaterialSQLDao;
import com.recycling.Rest.Dao.PositionSQLDao;
import com.recycling.Rest.Service.StationService;
import com.recycling.production.Material;
import com.recycling.production.Position;
import com.recycling.production.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    private PositionSQLDao positionSQLDao;
    @Autowired
    private MaterialSQLDao materialSQLDao;
    @Autowired
    private CleaningScheduleSQLDao cleaningScheduleSQLDao;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @RequestMapping(value = "/fromPosition", method = RequestMethod.GET)
    public Station getStationFromPosition(Position p){
        return stationService.getStationFromPosition(p);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addStation(@RequestBody final Station s){
        if (s.getPos() != null) {
            positionSQLDao.addPosition(s.getPos());
        }
        if(s.getAvailableMaterials() != null){
            for(Material m : s.getAvailableMaterials()){
                materialSQLDao.addmaterial(m);
            }
        }
        if(s.getCleaningSchedule()!=null){
            cleaningScheduleSQLDao.addCleaningSchedule(s.getCleaningSchedule());
        }
        stationService.addStation(s);
    }

}
