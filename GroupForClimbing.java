package jpa.entity.coursework4;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

@Entity
public class GroupForClimbing {

    @EmbeddedId
    private GroupForClimbingKey key;

    private boolean isGroupRecruitment;

//    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
//    private List<Climber> groupOfClimbers;
//
//    @ManyToMany(mappedBy = "group")
//    private List <Mountain> mountain;

    @Column(nullable = false)
    LocalDateTime climbingData;

    @Column(nullable = false)
    LocalDateTime duration;

    public GroupForClimbing(boolean isGroupRecruitment, List <Mountain> mountain, List<Climber> groupOfClimbers) {
        setMountain(mountain);
        setClimbers(groupOfClimbers);
        setGroupRecruitment(isGroupRecruitment);
    }

    public GroupForClimbing() {
    }

    public boolean isGroupRecruitment() {
        return isGroupRecruitment;
    }

    public void setGroupRecruitment(boolean groupRecruitment) {
        isGroupRecruitment = groupRecruitment;
    }

    public List<Climber> getClimbers() {
        return groupOfClimbers;
    }

    public void setClimbers(List<Climber> groupOfClimbers) {
        if (groupOfClimbers == null)
            throw new IllegalArgumentException("groupOfClimbers не должен быть равен null");
        this.groupOfClimbers = groupOfClimbers;
    }

    public List <Mountain> getMountain() {
        return mountain;
    }

    public void setMountain(List <Mountain> mountain) {
        if (mountain == null)
            throw new IllegalArgumentException("mountain не должен быть равен null");
        this.mountain = mountain;
    }

    public void addClimbers(Climber climber) {
        groupOfClimbers.add(climber);
    }

    @Embeddable // для составного первичного ключа
    public static class GroupForClimbingKey implements Serializable {
        final static long serialVersionUID = 1L;

        @Column(length = 20)
        private Climber climber;

        @Column(length = 30)
        private Mountain mountain;

        public GroupForClimbingKey(){

        }

        public Mountain getMountain() {
            return mountain;
        }

        public void setMountain(Mountain mountain) {
            if (mountain == null)
                throw new IllegalArgumentException("mountain не должен быть равен null");
            this.mountain = mountain;
        }

        public Climber getClimber() {
            return climber;
        }

        public void setClimber(Climber climber) {
            if (climber == null)
                throw new IllegalArgumentException("climber не должен быть равен null");
            this.climber = climber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupForClimbingKey key = (GroupForClimbingKey) o;
            return Objects.equals(climber, key.climber) &&
                    Objects.equals(mountain, key.mountain);
        }

        @Override
        public int hashCode() {
            return Objects.hash(climber, mountain);
        }
    }
}
