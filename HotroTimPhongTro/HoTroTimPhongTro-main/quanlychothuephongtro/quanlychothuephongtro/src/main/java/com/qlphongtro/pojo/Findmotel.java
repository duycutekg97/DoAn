/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "findmotel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Findmotel.findAll", query = "SELECT f FROM Findmotel f"),
    @NamedQuery(name = "Findmotel.findById", query = "SELECT f FROM Findmotel f WHERE f.id = :id"),
    @NamedQuery(name = "Findmotel.findByCity", query = "SELECT f FROM Findmotel f WHERE f.city = :city"),
    @NamedQuery(name = "Findmotel.findByDistrict", query = "SELECT f FROM Findmotel f WHERE f.district = :district"),
    @NamedQuery(name = "Findmotel.findByCreatedDate", query = "SELECT f FROM Findmotel f WHERE f.createdDate = :createdDate")})
public class Findmotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "detaileddescription")
    private String detaileddescription;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "map")
    private String map;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "district")
    private String district;
    @Size(max = 45)
    @Column(name = "created_date")
    private String createdDate;
    @OneToMany(mappedBy = "fkfindmotelfindmotelId")
    @JsonIgnore
    private Set<Findmotel> findmotelSet;
    @JoinColumn(name = "fkfindmotel_findmotelId", referencedColumnName = "id")
    @ManyToOne
    private Findmotel fkfindmotelfindmotelId;
    @OneToMany(mappedBy = "fkfindmotelfindmotelIdReply")
    @JsonIgnore
    private Set<Findmotel> findmotelSet1;
    @JoinColumn(name = "fkfindmotel_findmotelIdReply", referencedColumnName = "id")
    @ManyToOne
    private Findmotel fkfindmotelfindmotelIdReply;
    @JoinColumn(name = "fkfindmotel_userId", referencedColumnName = "id")
    @ManyToOne
    private User fkfindmoteluserId;

    public Findmotel() {
    }

    public Findmotel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetaileddescription() {
        return detaileddescription;
    }

    public void setDetaileddescription(String detaileddescription) {
        this.detaileddescription = detaileddescription;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<Findmotel> getFindmotelSet() {
        return findmotelSet;
    }

    public void setFindmotelSet(Set<Findmotel> findmotelSet) {
        this.findmotelSet = findmotelSet;
    }

    public Findmotel getFkfindmotelfindmotelId() {
        return fkfindmotelfindmotelId;
    }

    public void setFkfindmotelfindmotelId(Findmotel fkfindmotelfindmotelId) {
        this.fkfindmotelfindmotelId = fkfindmotelfindmotelId;
    }

    @XmlTransient
    public Set<Findmotel> getFindmotelSet1() {
        return findmotelSet1;
    }

    public void setFindmotelSet1(Set<Findmotel> findmotelSet1) {
        this.findmotelSet1 = findmotelSet1;
    }

    public Findmotel getFkfindmotelfindmotelIdReply() {
        return fkfindmotelfindmotelIdReply;
    }

    public void setFkfindmotelfindmotelIdReply(Findmotel fkfindmotelfindmotelIdReply) {
        this.fkfindmotelfindmotelIdReply = fkfindmotelfindmotelIdReply;
    }

    public User getFkfindmoteluserId() {
        return fkfindmoteluserId;
    }

    public void setFkfindmoteluserId(User fkfindmoteluserId) {
        this.fkfindmoteluserId = fkfindmoteluserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Findmotel)) {
            return false;
        }
        Findmotel other = (Findmotel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlphongtro.pojo.Findmotel[ id=" + id + " ]";
    }
    
}
