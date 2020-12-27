package jpa.entity.coursework4;

import jpa.entity.coursework4.Dao;
import jpa.entity.coursework4.Mountain;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MountainDao implements Dao<Mountain, Integer> {
    private EntityManager manager;

    public MountainDao(EntityManager mountainManager) {
        this.manager = mountainManager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);

    }

    @Override
    public Mountain getByPK(Integer integer) {
        return manager.find(Mountain.class, integer);
    }

    @Override
    public void delete(Mountain mountain) {
        manager.remove(mountain);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Mountain mountain = getByPK(integer);
        if (mountain != null)
            delete(mountain);
        else System.out.println("Такой горы не существует");

    }

    public List<Mountain> getMountainByCountry (String country) {
        TypedQuery <Mountain> query = manager.createNamedQuery("Mountain.getMountainByCountryName",
                Mountain.class);
        query.setParameter("country", country);
        List <Mountain> mountains = query.getResultList();
        return mountains;
    }

}
