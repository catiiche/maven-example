package jpa.entity.coursework4;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClimberDao implements Dao<Climber, Integer> {

    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);

    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);

    }

    @Override
    public Climber getByPK(Integer integer) {
        return  manager.find(Climber.class, integer);
    }

    @Override
    public void delete(Climber climber) {
        manager.remove(climber);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Climber climber = getByPK(integer);
        if (climber != null)
            delete(climber);
        else System.out.println("Такого альпиниста не существует");

    }

    public List<Climber> getClimbers(int from, int to){
        TypedQuery<Climber> query = manager.createNamedQuery("Climber.getByAge",
                Climber.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List <Climber> climbers = query.getResultList();
        return climbers;
    }

}
