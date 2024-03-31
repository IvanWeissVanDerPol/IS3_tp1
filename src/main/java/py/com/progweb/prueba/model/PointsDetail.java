package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point_usage_detail")
public class PointsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private PointsHeader pointsHeader;

    @Column(name = "points_used")
    private Integer pointsUsed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "points_wallet_id", referencedColumnName = "id")
    private PointWallet pointWallet;

    // Constructors
    public PointsDetail() {
        // Default constructor
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointsHeader getPointsHeader() {
        return pointsHeader;
    }

    public void setPointsHeader(PointsHeader pointsHeader) {
        this.pointsHeader = pointsHeader;
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public Long getPointWallet() {
        return pointWallet.getId();
    }

    public void setPointWallet(Long pointsWalletId) {
        this.pointWallet.setId(pointsWalletId);
    }

    public int getPointsRemaining() {
        return 0;
    }

    public void setUsageDate(Date date) {
    }

    public void setPointsRemaining(int remainingPoints) {
    }

    // Override toString, equals and hashCode methods if necessary
}
