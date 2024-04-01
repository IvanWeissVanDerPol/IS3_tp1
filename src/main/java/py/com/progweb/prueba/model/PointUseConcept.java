package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "point_use_concept")
public class PointUseConcept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "points_required")
    @JsonProperty("points_required") // This annotation helps Jackson recognize the field from JSON
    private Integer requiredPoints;

    public PointUseConcept() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(Integer requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    @Override
    public String toString() {
        return "PointUseConcept{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", requiredPoints=" + requiredPoints +
                '}';
    }
}
