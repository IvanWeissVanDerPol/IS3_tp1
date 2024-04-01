package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point_wallet")
public class PointWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "assignment_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date assignmentDate = new Date();

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @Column(name = "assigned_points")
    private Integer assignedPoints;

    @Column(name = "used_points")
    private Integer usedPoints = 0;

    @Column(name = "points_balance")
    private Integer pointsBalance;

    @Column(name = "transaction_amount")
    private Double transactionAmount;


    // Constructors

    public PointWallet() {
    }

    public PointWallet(Integer customerId, Integer assignedPoints,Date expirationDate) {
        this.customerId = customerId;
        this.assignedPoints = assignedPoints;
        this.pointsBalance = assignedPoints;
        this.transactionAmount = (double) 0;
        this.assignmentDate = new Date();
        // calculate expiration date based on the point expiration rule
        // if the current date is between the start and end date of the rule, the expiration date is the current date plus the rule's validity period
        this.expirationDate = expirationDate;
        this.usedPoints = 0;
    }
    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getAssignedPoints() {
        return assignedPoints;
    }

    public void setAssignedPoints(Integer assignedPoints) {
        this.assignedPoints = assignedPoints;
    }

    public Integer getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(Integer usedPoints) {
        this.usedPoints = usedPoints;
    }

    public Integer getPointsBalance() {
        return pointsBalance;
    }

    public void setPointsBalance(Integer pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "PointWallet{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", assignmentDate=" + assignmentDate +
                ", expirationDate=" + expirationDate +
                ", assignedPoints=" + assignedPoints +
                ", usedPoints=" + usedPoints +
                ", pointsBalance=" + pointsBalance +
                ", transactionAmount=" + transactionAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointWallet that = (PointWallet) o;

        if (!id.equals(that.id)) return false;
        return customerId.equals(that.customerId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

//    public Customer getCustomer() {
//        CustomerService customerService = new CustomerService();
//        return customerService.findCustomerById(this.customerId);
//
//    }




    
}
