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
@Table(name = "evaluate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluate.findAll", query = "SELECT e FROM Evaluate e"),
    @NamedQuery(name = "Evaluate.findById", query = "SELECT e FROM Evaluate e WHERE e.id = :id"),
    @NamedQuery(name = "Evaluate.findByComment", query = "SELECT e FROM Evaluate e WHERE e.comment = :comment"),
    @NamedQuery(name = "Evaluate.findByCreatedDate", query = "SELECT e FROM Evaluate e WHERE e.createdDate = :createdDate")})
public class Evaluate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "comment")
    private String comment;
    @Size(max = 45)
    @Column(name = "created_date")
    private String createdDate;
    @OneToMany(mappedBy = "fkevaluteevaluateId")
    @JsonIgnore
    private Set<Evaluate> evaluateSet;
    @JoinColumn(name = "fkevalute_evaluateId", referencedColumnName = "id")
    @ManyToOne
    private Evaluate fkevaluteevaluateId;
    @OneToMany(mappedBy = "fkevaluteevaluateIdReply")
    @JsonIgnore
    private Set<Evaluate> evaluateSet1;
    @JoinColumn(name = "fkevalute_evaluateIdReply", referencedColumnName = "id")
    @ManyToOne
    private Evaluate fkevaluteevaluateIdReply;
    @JoinColumn(name = "fkevalute_motelId", referencedColumnName = "id")
    @ManyToOne
    private Motel fkevalutemotelId;
    @JoinColumn(name = "fkevalute_userIdRespondent", referencedColumnName = "id")
    @ManyToOne
    private User fkevaluteuserIdRespondent;

    public Evaluate() {
    }

    public Evaluate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<Evaluate> getEvaluateSet() {
        return evaluateSet;
    }

    public void setEvaluateSet(Set<Evaluate> evaluateSet) {
        this.evaluateSet = evaluateSet;
    }

    public Evaluate getFkevaluteevaluateId() {
        return fkevaluteevaluateId;
    }

    public void setFkevaluteevaluateId(Evaluate fkevaluteevaluateId) {
        this.fkevaluteevaluateId = fkevaluteevaluateId;
    }

    @XmlTransient
    public Set<Evaluate> getEvaluateSet1() {
        return evaluateSet1;
    }

    public void setEvaluateSet1(Set<Evaluate> evaluateSet1) {
        this.evaluateSet1 = evaluateSet1;
    }

    public Evaluate getFkevaluteevaluateIdReply() {
        return fkevaluteevaluateIdReply;
    }

    public void setFkevaluteevaluateIdReply(Evaluate fkevaluteevaluateIdReply) {
        this.fkevaluteevaluateIdReply = fkevaluteevaluateIdReply;
    }

    public Motel getFkevalutemotelId() {
        return fkevalutemotelId;
    }

    public void setFkevalutemotelId(Motel fkevalutemotelId) {
        this.fkevalutemotelId = fkevalutemotelId;
    }

    public User getFkevaluteuserIdRespondent() {
        return fkevaluteuserIdRespondent;
    }

    public void setFkevaluteuserIdRespondent(User fkevaluteuserIdRespondent) {
        this.fkevaluteuserIdRespondent = fkevaluteuserIdRespondent;
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
        if (!(object instanceof Evaluate)) {
            return false;
        }
        Evaluate other = (Evaluate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlphongtro.pojo.Evaluate[ id=" + id + " ]";
    }
    
}
