package jpa.entity.coursework4;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupForClimbingDao implements Dao<GroupForClimbing, Integer> {

    private EntityManager manager;

    public GroupForClimbingDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(GroupForClimbing groupForClimbing) {
        manager.persist(groupForClimbing);
    }

    @Override
    public void update(GroupForClimbing groupForClimbing) {
        manager.merge(groupForClimbing);
    }

    @Override
    public GroupForClimbing getByPK(Integer integer) {
        return manager.find(GroupForClimbing.class, integer);
    }

    @Override
    public void delete(GroupForClimbing groupForClimbing) {
        manager.remove(groupForClimbing);

    }

    @Override
    public void deleteByPK(Integer integer) {
        GroupForClimbing group = getByPK(integer);
        if (group != null)
            delete(group);
        else System.out.println("Такой группы не существует");
    }

    public List<GroupForClimbing> getByMountain(String name) {
        TypedQuery<GroupForClimbing> query = manager.createNamedQuery("GroupForClimbing.getByMountain",
                GroupForClimbing.class);
        query.setParameter("name", name);
        List<GroupForClimbing> groups = query.getResultList();
        return groups;
    }

    public List<GroupForClimbing> getOpenGroup(Boolean condition) {
        TypedQuery<GroupForClimbing> query = manager.createNamedQuery("GroupForClimbing.getOpen",
                GroupForClimbing.class);
        query.setParameter("condition", condition);
        List<GroupForClimbing> groups = query.getResultList();
        return groups;

    }
}

