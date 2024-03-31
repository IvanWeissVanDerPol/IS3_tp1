

package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "point_allocation_rule")
public class PointAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lower_limit")
    private Long lowerLimit;

    @Column(name = "upper_limit")
    private Long upperLimit;

    @Column(name = "points_per_unit")
    private Integer pointsPerUnit;

    // Constructors
    public PointAllocationRule() {
    }

    public PointAllocationRule(Long lowerLimit, Long upperLimit, Integer pointsPerUnit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.pointsPerUnit = pointsPerUnit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Long getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Long upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getPointsPerUnit() {
        return pointsPerUnit;
    }

    public void setPointsPerUnit(Integer pointsPerUnit) {
        this.pointsPerUnit = pointsPerUnit;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "PointAllocationRule{" +
                "id=" + id +
                ", lowerLimit=" + lowerLimit +
                ", upperLimit=" + upperLimit +
                ", pointsPerUnit=" + pointsPerUnit +
                '}';
    }
}
