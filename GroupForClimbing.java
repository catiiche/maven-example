package jpa.entity.coursework4;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        // 2.Getting a list of Groups by Mountain's name
        @NamedQuery(name = "GroupForClimbing.getByMountain",
                query = "SELECT a FROM GroupForClimbing a JOIN FETCH a.mountain s WHERE s.name = :name"),

        //4.Getting groups that are still open
        @NamedQuery(name = "GroupForClimbing.getOpen",
                query = "SELECT a FROM GroupForClimbing a WHERE a.isGroupRecruitment = :condition"),
})
public class GroupForClimbing extends AutoPrimaryKey {

    @JoinColumn(nullable = false)
    private boolean isGroupRecruitment;

    @ManyToMany
    @JoinTable(name = "climbers_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "climber_id"))
    private List<Climber> groupOfClimbers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @Column(nullable = false)
    LocalDateTime climbingData;

    @Column(nullable = false)
    LocalDateTime duration;

    public GroupForClimbing(boolean isGroupRecruitment, Mountain mountain, LocalDateTime climbingData
            , LocalDateTime duration, List<Climber> groupOfClimbers) {
        setMountain(mountain);
        setGroupRecruitment(isGroupRecruitment);
        setClimbingData(climbingData);
        setDuration(duration);
        setGroupOfClimbers(groupOfClimbers);
    }

    public GroupForClimbing() {
    }

    public List<Climber> getGroupOfClimbers() {
        return groupOfClimbers;
    }

    public void setGroupOfClimbers(List<Climber> groupOfClimbers) {
        this.groupOfClimbers = groupOfClimbers;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public LocalDateTime getClimbingData() {
        return climbingData;
    }

    public void setClimbingData(LocalDateTime climbingData) {
        this.climbingData = climbingData;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        if (duration == null || duration.isBefore(this.climbingData))
            throw new IllegalArgumentException("duration должен быть позже climbingData");
        this.duration = duration;
    }

    public boolean isGroupRecruitment() {
        return isGroupRecruitment;
    }

    public void setGroupRecruitment(boolean groupRecruitment) {
        isGroupRecruitment = groupRecruitment;
    }

    public void addClimber(Climber climber) {
        groupOfClimbers.add(climber);
    }
}
