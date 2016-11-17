/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utils.DateAdapter;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @XmlElement(name = "email")
    private String entityEmail;

    @XmlElement(name = "name")
    private String entityName;

    @XmlElement(name = "first-name")
    private String entityNameFirst;

    @XmlElement(name = "password")
    private String entityPassword;

    @Temporal(TemporalType.DATE)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "date-creation")
    private Date entityDateCreation = new Date();

    @XmlElement(name = "phone-number")
    private String entityNumberPhone;

    @Temporal(TemporalType.DATE)
    @XmlElement(name = "date-birthday")
    private Date entityDateBirthday;

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
        if (!(object instanceof EntityUser)) {
            return false;
        }
        EntityUser other = (EntityUser) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EntityUser[ id=" + getId() + " ]";
    }

    /**
     * @return the entityEmail
     */
    public String getEntityEmail() {
        return entityEmail;
    }

    /**
     * @param entityEmail the entityEmail to set
     */
    public void setEntityEmail(String entityEmail) {
        this.entityEmail = entityEmail;
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
     * @return the entityNameFirst
     */
    public String getEntityNameFirst() {
        return entityNameFirst;
    }

    /**
     * @param entityNameFirst the entityNameFirst to set
     */
    public void setEntityNameFirst(String entityNameFirst) {
        this.entityNameFirst = entityNameFirst;
    }

    /**
     * @return the entityDateCreation
     */
    public Date getEntityDateCreation() {
        return entityDateCreation;
    }

    /**
     * @return the entityPassword
     */
    public String getEntityPassword() {
        return entityPassword;
    }

    /**
     * @param entityPassword the entityPassword to set
     */
    public void setEntityPassword(String entityPassword) {
        this.entityPassword = entityPassword;
    }

    /**
     * @return the entityNumberPhone
     */
    public String getEntityNumberPhone() {
        return entityNumberPhone;
    }

    /**
     * @param entityNumberPhone the entityNumberPhone to set
     */
    public void setEntityNumberPhone(String entityNumberPhone) {
        this.entityNumberPhone = entityNumberPhone;
    }

    /**
     * @return the entityDateBirthday
     */
    public Date getEntityDateBirthday() {
        return entityDateBirthday;
    }

    /**
     * @param entityDateBirthday the entityDateBirthday to set
     */
    public void setEntityDateBirthday(Date entityDateBirthday) {
        this.entityDateBirthday = entityDateBirthday;
    }

    /**
     * @param entityDateCreation the entityDateCreation to set
     */
    public void setEntityDateCreation(Date entityDateCreation) {
        this.entityDateCreation = entityDateCreation;
    }

}
