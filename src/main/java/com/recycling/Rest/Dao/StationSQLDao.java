package com.recycling.Rest.Dao;

import com.recycling.DB.repository.CleaningScheduleRepository;
import com.recycling.DB.repository.MaterialScheduleRepository;
import com.recycling.DB.repository.StationRepository;
import com.recycling.production.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Repository
public class StationSQLDao {
    //TODO: sortera stationer? Utifrån

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CleaningScheduleRepository cleaningScheduleRepository;
    @Autowired
    private MaterialScheduleRepository materialScheduleRepository;

    public Collection<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(int id) {
        return null;
    }

    public void removeStationById(int id) {

    }

    public void updateStation(Station updatedStation) {
//        for (Station s : stationRepository.findAll()) {
//            if (s.getStationName() == updatedStation.getStationName()) {
//                s.setAvailableMaterials(updatedStation.getAvailableMaterials());
//                s.setPos(updatedStation.getPos());
//                return;
//            }
//        }
        stationRepository.save(updatedStation);
    }

    public Station getStationFromPosition(Position p) {
        for (Station s : stationRepository.findAll()) {
            if (s.getPos().equals(p))
                return s;
        }
        return null;
    }

    public void addStation(Station newStation) {
        stationRepository.save(newStation);
    }

    //TODO: hämta datum, ifall datum är över current date/time, uppdatera datumet som hämtats och öka med två/en dagar
    @Scheduled(cron = "0 0 * * * *")
    public void checkSchedules() {
        Date currentDate = new Date();
        for (Station s : stationRepository.findAll()) {
            if (s.getCleaningSchedule() != null)
                if (currentDate.after(s.getCleaningSchedule().getDate())) {
                    CleaningSchedule newCleaningSchedule = new CleaningSchedule(new Date(s.getCleaningSchedule().getDate().getTime() + TimeUnit.DAYS.toMillis(1)));
                    s.setCleaningSchedule(newCleaningSchedule);
                    cleaningScheduleRepository.save(newCleaningSchedule);
                }
            if (!s.getMaterialSchedules().isEmpty()) {
                Collection<MaterialSchedule> newMaterialSchedules = new LinkedList<>();
                for (MaterialSchedule ms : s.getMaterialSchedules()) {
                    if (currentDate.after(ms.getDate())) {
                        MaterialSchedule newMs = new MaterialSchedule(new Date(ms.getDate().getTime() + TimeUnit.DAYS.toMillis(2)), ms.getMaterial());
                        materialScheduleRepository.save(newMs);
                    }
                }
                s.setMaterialSchedules(newMaterialSchedules);
                stationRepository.save(s);
            }
        }
    }
}
