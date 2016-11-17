/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utils.DateAdapter;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityTrip implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlTransient
    private boolean entityTripComplete = false;

    @XmlElement(name = "name")
    private String entityName;

    @XmlElement(name = "description")
    private String entityDescription;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @Temporal(TemporalType.DATE)
    @XmlElement(name = "date-creation")
    private Date entityDateCreation = new Date();

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "date-from")
    @Temporal(TemporalType.DATE)
    private Date entityDateFrom;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "date-to")
    @Temporal(TemporalType.DATE)
    private Date entityDateTo;

    @XmlElement(name = "user-organizer")
    @ManyToOne
    private EntityUser entityUserOrganizer;

    @XmlElement(name = "users-participer")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<EntityUser> entityUsersParticiper = new ArrayList<>();

    @XmlElement(name = "price")
    private double amountOfTrip;

    @XmlElement(name = "number-participers")
    private short numberOfParticiper;

    @XmlElement(name = "number-insiders")
    private short numberOfUsersInside;

    @XmlElement(name = "address-from")
    private String entityAddressFrom;

    @XmlElement(name = "address-to")
    private String entityAddressTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityTrip)) {
            return false;
        }
        EntityTrip other = (EntityTrip) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EntityTrip[ id=" + getId() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the entityDescription
     */
    public String getEntityDescription() {
        return entityDescription;
    }

    /**
     * @param entityDescription the entityDescription to set
     */
    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    /**
     * @return the entityDateCreation
     */
    public Date getEntityDateCreation() {
        return entityDateCreation;
    }

    /**
     * @return the entityDateFrom
     */
    public Date getEntityDateFrom() {
        return entityDateFrom;
    }

    /**
     * @param entityDateFrom the entityDateFrom to set
     */
    public void setEntityDateFrom(Date entityDateFrom) {
        this.entityDateFrom = entityDateFrom;
    }

    /**
     * @return the entityDateTo
     */
    public Date getEntityDateTo() {
        return entityDateTo;
    }

    /**
     * @param entityDateTo the entityDateTo to set
     */
    public void setEntityDateTo(Date entityDateTo) {
        this.entityDateTo = entityDateTo;
    }

    /**
     * @return the entityUserOrganizer
     */
    public EntityUser getEntityUserOrganizer() {
        return entityUserOrganizer;
    }

    /**
     * @param entityUserOrganizer the entityUserOrganizer to set
     */
    public void setEntityUserOrganizer(EntityUser entityUserOrganizer) {
        this.entityUserOrganizer = entityUserOrganizer;
    }

    /**
     * @return the entityUsersParticiper
     */
    public List<EntityUser> getEntityUsersParticiper() {
        return entityUsersParticiper;
    }

    /**
     * @param entityUsersParticiper the entityUsersParticiper to set
     */
    public void setEntityUsersParticiper(List<EntityUser> entityUsersParticiper) {
        this.entityUsersParticiper = entityUsersParticiper;
    }

    /**
     * @return the amountOfTrip
     */
    public double getAmountOfTrip() {
        return amountOfTrip;
    }

    /**
     * @param amountOfTrip the amountOfTrip to set
     */
    public void setAmountOfTrip(double amountOfTrip) {
        this.amountOfTrip = amountOfTrip;
    }

    /**
     * @return the numberOfParticiper
     */
    public short getNumberOfParticiper() {
        return numberOfParticiper;
    }

    /**
     * @param numberOfParticiper the numberOfParticiper to set
     */
    public void setNumberOfParticiper(short numberOfParticiper) {
        this.numberOfParticiper = numberOfParticiper;
    }

    /**
     * @return the numberOfUsersInside
     */
    public short getNumberOfUsersInside() {
        return numberOfUsersInside;
    }

    /**
     * @param numberOfUsersInside the numberOfUsersInside to set
     */
    public void setNumberOfUsersInside(short numberOfUsersInside) {
        this.numberOfUsersInside = numberOfUsersInside;
    }

    /**
     * @return the entityAddressFrom
     */
    public String getEntityAddressFrom() {
        return entityAddressFrom;
    }

    /**
     * @param entityAddressFrom the entityAddressFrom to set
     */
    public void setEntityAddressFrom(String entityAddressFrom) {
        this.entityAddressFrom = entityAddressFrom;
    }

    /**
     * @return the entityAddressTo
     */
    public String getEntityAddressTo() {
        return entityAddressTo;
    }

    /**
     * @param entityAddressTo the entityAddressTo to set
     */
    public void setEntityAddressTo(String entityAddressTo) {
        this.entityAddressTo = entityAddressTo;
    }

    /**
     * @return the entityTripComplete
     */
    public boolean isEntityTripComplete() {
        return entityTripComplete;
    }

    /**
     * @param entityTripComplete the entityTripComplete to set
     */
    public void setEntityTripComplete(boolean entityTripComplete) {
        this.entityTripComplete = entityTripComplete;
    }

    /**
     * @param entityDateCreation the entityDateCreation to set
     */
    public void setEntityDateCreation(Date entityDateCreation) {
        this.entityDateCreation = entityDateCreation;
    }

}
