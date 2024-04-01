package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "point_usage_header")
public class PointsHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "points_used")
    private Integer pointsUsed;

    @Column(name = "usage_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usageDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_use_concept_id", referencedColumnName = "id")
    private PointUseConcept pointUseConcept;

    @OneToMany(mappedBy = "pointsHeader", cascade = CascadeType.ALL)
    private List<PointsDetail> details;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customer.getId();
    }

    public void setCustomerId(Integer customerId) {this.customer.setId(customerId);}

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

    public Integer getPointUseConcept_Id() {
        return pointUseConcept.getId();
    }

    public void setPointUseConcept_Id(Integer pointUseConcept) {
        this.pointUseConcept.setId(pointUseConcept);;
    }

    public PointUseConcept getPointUseConcept() {
        return pointUseConcept;
    }

    public void setPointUseConcept(PointUseConcept pointUseConcept) {
        this.pointUseConcept =  pointUseConcept;
    }


    public List<PointsDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PointsDetail> details) {
        this.details = details;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
