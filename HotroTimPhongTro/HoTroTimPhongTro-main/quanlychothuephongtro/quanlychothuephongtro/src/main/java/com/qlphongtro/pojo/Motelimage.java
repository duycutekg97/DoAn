/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "motelimage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motelimage.findAll", query = "SELECT m FROM Motelimage m"),
    @NamedQuery(name = "Motelimage.findById", query = "SELECT m FROM Motelimage m WHERE m.id = :id"),
    @NamedQuery(name = "Motelimage.findByImage", query = "SELECT m FROM Motelimage m WHERE m.image = :image")})
public class Motelimage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "fkmotelimage_motelId", referencedColumnName = "id")
    @ManyToOne
    private Motel fkmotelimagemotelId;

    public Motelimage() {
    }

    public Motelimage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Motel getFkmotelimagemotelId() {
        return fkmotelimagemotelId;
    }

    public void setFkmotelimagemotelId(Motel fkmotelimagemotelId) {
        this.fkmotelimagemotelId = fkmotelimagemotelId;
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
        if (!(object instanceof Motelimage)) {
            return false;
        }
        Motelimage other = (Motelimage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlphongtro.pojo.Motelimage[ id=" + id + " ]";
    }
    
}
