package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "point_usage_header")
public class PointsHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "points_used")
    private Integer pointsUsed;

    @Column(name = "usage_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usageDate;

    @Column(name = "point_use_concept")
    private String pointUseConcept;

    @OneToMany(mappedBy = "pointsHeader", cascade = CascadeType.ALL)
    private List<PointsDetail> details;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customer.getId();
    }

    public void setCustomerId(Integer customerId) {
        this.customer.setId(customerId);
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }

    public String getPointUseConcept() {
        return pointUseConcept;
    }

    public void setPointUseConcept(String pointUseConcept) {
        this.pointUseConcept = pointUseConcept;
    }

    public List<PointsDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PointsDetail> details) {
        this.details = details;
    }
}
