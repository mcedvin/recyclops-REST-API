package com.recycling.Test.Controller;


import com.recycling.Test.Dao.MaterialSQLDao;
import com.recycling.Test.Dao.PositionSQLDao;
import com.recycling.Test.Service.StationService;
import com.recycling.recycling.production.Material;
import com.recycling.recycling.production.Station;
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

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Station> getAllStations() {
        return stationService.getAllStations();
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
        stationService.addStation(s);
    }

}
