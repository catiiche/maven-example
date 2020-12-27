package jpa.entity.coursework4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // factory used to manage entities. To connect server, used settings  in persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        GroupForClimbingDao daoGroup = new GroupForClimbingDao(manager);
        ClimberDao daoClimber = new ClimberDao(manager);
        MountainDao daoMountain = new MountainDao(manager);

        Mountain mountain1 = new Mountain("Demerji", "Russia", 1356);
        Mountain mountain2 = new Mountain("Mont Blanc", "Italy", 4810);
        Mountain mountain3 = new Mountain("Chimtarga", "Tajikistan", 5489);
        Mountain mountain4 = new Mountain("Kackar ", "Turkey", 3937);
        Mountain mountain5 = new Mountain("Tsurugi ", "Japan", 2999);

        List<Mountain> mountains = new ArrayList<>();
        mountains.add(mountain1);
        mountains.add(mountain2);
        mountains.add(mountain3);
        mountains.add(mountain4);
        mountains.add(mountain5);

        Climber climber1 = new Climber("John", "London", 73);
        Climber climber2 = new Climber("Jack", "Texas", 25);
        Climber climber3 = new Climber("Ivan", "Saint-Petersburg", 45);
        Climber climber4 = new Climber("Sveta", "Japan", 65);
        Climber climber5 = new Climber("Olga", "Saint-Petersburg", 18);
        Climber climber6 = new Climber("Samuel", "Tallin", 28);

        List<Climber> group1 = new ArrayList<>();
        group1.add(climber1);
        group1.add(climber2);
        group1.add(climber3);

        List<Climber> group2 = new ArrayList<>();
        group2.add(climber4);
        group2.add(climber5);
        group2.add(climber6);

        GroupForClimbing groupForClimbing1 = new GroupForClimbing(true, mountain1, LocalDateTime.now(),
                LocalDateTime.now().plusDays(15), group1);
        GroupForClimbing groupForClimbing2 = new GroupForClimbing(true, mountain2, LocalDateTime.now(),
                LocalDateTime.now().plusDays(5), group2);

        List<GroupForClimbing> groups = new ArrayList<>();
        groups.add(groupForClimbing1);
        groups.add(groupForClimbing2);

        manager.getTransaction().begin();
        for (Mountain mountain : mountains) {
            daoMountain.add(mountain);
        }

        manager.getTransaction().commit(); // confirm the transaction

        manager.getTransaction().begin();
        for (Climber climber : group1) {
            daoClimber.add(climber);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        for (Climber climber : group2) {
            daoClimber.add(climber);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        for (GroupForClimbing group : groups) {
            daoGroup.add(group);
        }
        manager.getTransaction().commit();

        // 1. Getting all members aged [from; to)
        manager.getTransaction().begin();
        List<Climber> climberList = daoClimber.getClimbers(18, 27);
        manager.getTransaction().commit();
        for (Climber climber : climberList) {
            System.out.println("Name: " + climber.getName() + "\n" +
                    climber.getAge());
        }

        // 2.Getting a list of Groups by Mountain's name
        manager.getTransaction().begin();
        List<GroupForClimbing> groupClimbers = daoGroup.getByMountain("Demerji");
        manager.getTransaction().commit();
        for (GroupForClimbing group : groupClimbers) {
            System.out.println("id" + group.getId() + "\n" +
                    "mountain: " + group.getMountain().getName() + "\n" +
                    "date" + group.getClimbingData());
        }

        // 3. Getting a list of Mountains by country's name
        manager.getTransaction().begin();
        List<Mountain> mountains1 = daoMountain.getMountainByCountry("Italy");
        manager.getTransaction().commit();
        for (Mountain mountain : mountains1) {
            System.out.println("id: " + mountain.getId() + "\n" +
                    "Name: " + mountain.getName() + "\n");
        }

        //4.Getting groups that are still open
        manager.getTransaction().begin();
        List<GroupForClimbing> groupsForClimbing1 = daoGroup.getOpenGroup(true);
        manager.getTransaction().commit();
        for (GroupForClimbing groupForClimbing : groupsForClimbing1) {
            System.out.println("id: " + groupForClimbing.getId() + "\n" +
                    "Mountain: " + groupForClimbing.getMountain().getName() + "\n" +
                    "Date: " + groupForClimbing.getDuration());
        }

        manager.close();
    }
}






