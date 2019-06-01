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
import java.util.concurrent.TimeUnit;

@Repository
public class StationSQLDao {

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

        @Scheduled(cron = "0 0 * * * *")
//    @Scheduled(cron = "*/10 * * * * *")

    public void checkSchedules() {
        Date currentDate = new Date();
        for (Station s : stationRepository.findAll()) {
            if (s.getCleaningSchedule() != null) {
                if (currentDate.after(s.getCleaningSchedule().getDate())) {
                    s.getCleaningSchedule().setDate(new Date(s.getCleaningSchedule().getDate().getTime() + TimeUnit.DAYS.toMillis(1)));
                    cleaningScheduleRepository.save(s.getCleaningSchedule());
                    stationRepository.save(s);
                }
            }
            //TODO: måste testa detta, nytt och ändrat
            //TODO: lägga in alla materialschedules igen
            if (!s.getMaterialSchedules().isEmpty()) {
                for (MaterialSchedule ms : s.getMaterialSchedules()) {
                    if (currentDate.after(ms.getDate())) {
                        ms.setDate(new Date(ms.getDate().getTime() + TimeUnit.DAYS.toMillis(2)));
                        materialScheduleRepository.save(ms);
                    }
                }
                stationRepository.save(s);
            }
        }
    }
}
