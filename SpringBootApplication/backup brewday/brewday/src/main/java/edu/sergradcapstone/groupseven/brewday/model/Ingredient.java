package edu.sergradcapstone.groupseven.brewday.model;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name="ingredientdummy")
public class Ingredient {

    protected Ingredient(){

    }

    //foreign key
    //user id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TIMESTAMP)
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    @Temporal(TIMESTAMP)
    @Column(name="last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="lastPurchasedDate")
    private Date lastPurchasedDate;

    @Column(name="mount")
    private double mount;

    @PrePersist
    protected void onCreate() {
        lastModifiedDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastPurchasedDate() {
        return lastPurchasedDate;
    }

    public void setLastPurchasedDate(Date lastPurchasedDate) {
        this.lastPurchasedDate = lastPurchasedDate;
    }

    public double getMount() {
        return mount;
    }

    public void setMount(double mount) {
        this.mount = mount;
    }
}