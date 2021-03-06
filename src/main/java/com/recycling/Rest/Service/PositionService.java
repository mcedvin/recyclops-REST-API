package com.recycling.Rest.Service;

import com.recycling.Rest.Dao.PositionSQLDao;
import com.recycling.production.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PositionService {

    @Autowired
//    @Qualifier("MySQL")
    private PositionSQLDao PositionDao;

    public Collection<Position> getAllPositions() {
        return PositionDao.getAllPositions();
    }
    public Position getPositionById(int id){
        return PositionDao.getPositionById(id);
    }
    public void removePositionById(int id){
        PositionDao.removePositionById(id);
    }
    public void updatePosition(Position Position){
        PositionDao.updatePosition(Position);
    }
    public void addPosition(Position Position){
        PositionDao.addPosition(Position);
    }
}
