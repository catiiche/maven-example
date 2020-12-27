package jpa.entity.coursework4;

import jpa.dao.GroupDao;
import jpa.dao.GroupForClimbingDao;
import jpa.entity.Group;
import jpa.specification.GroupByDuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Mountain mountain1 = new Mountain("Demerji", "Russia", 1356);
        Mountain mountain2 = new Mountain("Mont Blanc", "Italy", 4810);
        Mountain mountain3 = new Mountain("Chimtarga", "Tajikistan", 5489);
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(mountain1);
        mountains.add(mountain2);
        mountains.add(mountain3);

        Climber climber1 = new Climber("Ivan", "Saint-Petersburg", 45);

        List<Climber> group1 = new ArrayList<>();
        group1.add(climber1);
        System.out.println("Debug 1");

        GroupForClimbing groupForClimbing = new GroupForClimbing(true, mountains, group1);

        GroupForClimbing.GroupForClimbingKey key = new GroupForClimbing.GroupForClimbingKey();
        key.setClimber(climber1);
        key.setMountain(mountain1);



        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");

        EntityManager manager = factory.createEntityManager();


        manager.getTransaction().begin(); // начинаем транзакцию
        manager.persist(groupForClimbing); // метод используется для добавления в табличку
        manager.getTransaction().commit(); // подтверждаем транзакцию

        GroupForClimbingDao groupDao = new GroupForClimbingDao(manager);
        System.out.println(groupDao.getByPK(key)); // null


//        System.out.println(groupDao.getAll());
//
//        List<Group> groups = groupDao.getBySpecification(new GroupByDuration(3));
//        System.out.println(groups);


    }
}






