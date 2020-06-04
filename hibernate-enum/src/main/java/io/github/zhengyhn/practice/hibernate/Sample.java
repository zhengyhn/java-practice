package io.github.zhengyhn.practice.hibernate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sample")
public class Sample {
    @Id
    private Long id;

    @Column(name="lower_status")
    private BaStatus lowerStatus;

    @Column(name="upper_status")
    private ABStatus upperStatus;

    @Override
    public String toString() {
        return id + ", " + lowerStatus.name() + ", " + upperStatus.name();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLowerStatus(BaStatus lowerStatus) {
        this.lowerStatus = lowerStatus;
    }

    public void setUpperStatus(ABStatus upperStatus) {
        this.upperStatus = upperStatus;
    }
}
