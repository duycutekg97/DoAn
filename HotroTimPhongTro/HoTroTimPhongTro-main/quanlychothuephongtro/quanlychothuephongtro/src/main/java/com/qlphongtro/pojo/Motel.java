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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "motel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motel.findAll", query = "SELECT m FROM Motel m"),
    @NamedQuery(name = "Motel.findById", query = "SELECT m FROM Motel m WHERE m.id = :id"),
    @NamedQuery(name = "Motel.findByName", query = "SELECT m FROM Motel m WHERE m.name = :name"),
    @NamedQuery(name = "Motel.findByAddress", query = "SELECT m FROM Motel m WHERE m.address = :address"),
    @NamedQuery(name = "Motel.findByCityprovince", query = "SELECT m FROM Motel m WHERE m.cityprovince = :cityprovince"),
    @NamedQuery(name = "Motel.findByDistrict", query = "SELECT m FROM Motel m WHERE m.district = :district"),
    @NamedQuery(name = "Motel.findByPhone", query = "SELECT m FROM Motel m WHERE m.phone = :phone"),
    @NamedQuery(name = "Motel.findByNumberofresidents", query = "SELECT m FROM Motel m WHERE m.numberofresidents = :numberofresidents"),
    @NamedQuery(name = "Motel.findByPrice", query = "SELECT m FROM Motel m WHERE m.price = :price"),
    @NamedQuery(name = "Motel.findByTitle", query = "SELECT m FROM Motel m WHERE m.title = :title"),
    @NamedQuery(name = "Motel.findByAcreage", query = "SELECT m FROM Motel m WHERE m.acreage = :acreage"),
    @NamedQuery(name = "Motel.findByCreatedDate", query = "SELECT m FROM Motel m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "Motel.findByApply", query = "SELECT m FROM Motel m WHERE m.apply = :apply")})
public class Motel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 45)
    @Column(name = "cityprovince")
    private String cityprovince;
    @Size(max = 45)
    @Column(name = "district")
    private String district;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "map")
    private String map;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "phone")
    private String phone;
    @Column(name = "numberofresidents")
    private Integer numberofresidents;
    @Column(name = "price")
    private Long price;
    @Size(max = 100)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 10)
    @Column(name = "acreage")
    private String acreage;
    @Size(max = 20)
    @Column(name = "createdDate")
    private String createdDate;
    @Column(name = "apply")
    private Boolean apply;
    @OneToMany(mappedBy = "fkmotelimagemotelId")
    @JsonIgnore
    private Set<Motelimage> motelimageSet;
    @OneToMany(mappedBy = "fkevalutemotelId")
    @JsonIgnore
    private Set<Evaluate> evaluateSet;
    @JoinColumn(name = "fkmotel_userId", referencedColumnName = "id")
    @ManyToOne
    private User fkmoteluserId;
    @JsonIgnore
     @Transient
    private MultipartFile[] file = null;

    /**
     * @return the file
     */
    public MultipartFile[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile[] file) {
        this.file = file;
    }
    public Motel() {
    }

    public Motel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityprovince() {
        return cityprovince;
    }

    public void setCityprovince(String cityprovince) {
        this.cityprovince = cityprovince;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNumberofresidents() {
        return numberofresidents;
    }

    public void setNumberofresidents(Integer numberofresidents) {
        this.numberofresidents = numberofresidents;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getApply() {
        return apply;
    }

    public void setApply(Boolean apply) {
        this.apply = apply;
    }

    @XmlTransient
    public Set<Motelimage> getMotelimageSet() {
        return motelimageSet;
    }

    public void setMotelimageSet(Set<Motelimage> motelimageSet) {
        this.motelimageSet = motelimageSet;
    }

    @XmlTransient
    public Set<Evaluate> getEvaluateSet() {
        return evaluateSet;
    }

    public void setEvaluateSet(Set<Evaluate> evaluateSet) {
        this.evaluateSet = evaluateSet;
    }

    public User getFkmoteluserId() {
        return fkmoteluserId;
    }

    public void setFkmoteluserId(User fkmoteluserId) {
        this.fkmoteluserId = fkmoteluserId;
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
        if (!(object instanceof Motel)) {
            return false;
        }
        Motel other = (Motel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlphongtro.pojo.Motel[ id=" + id + " ]";
    }
    
}
