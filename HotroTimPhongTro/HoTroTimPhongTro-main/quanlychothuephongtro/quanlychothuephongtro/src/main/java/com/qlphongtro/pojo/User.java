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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByImage", query = "SELECT u FROM User u WHERE u.image = :image"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate")})
public class User implements Serializable {
    
    public static final String USER = "ROLE_USER";
    public static final String HOST = "ROLE_HOST";
    public static final String ADMIN = "ROLE_ADMIN";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @JsonIgnore
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 45)
    @Column(name = "createdDate")
    private String createdDate;
    @OneToMany(mappedBy = "fkfolloweruserHostId")
    @JsonIgnore
    private Set<Follower> followerSet;
    @OneToMany(mappedBy = "fkfolloweruserRenterId")
    @JsonIgnore
    private Set<Follower> followerSet1;
    @OneToMany(mappedBy = "fkfindmoteluserId")
    @JsonIgnore
    private Set<Findmotel> findmotelSet;
    @OneToMany(mappedBy = "fkevaluteuserIdRespondent")
    @JsonIgnore
    private Set<Evaluate> evaluateSet;
    @OneToMany(mappedBy = "fkmoteluserId")
    @JsonIgnore
    private Set<Motel> motelSet;
    @JoinColumn(name = "fkuser_roleuserId", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Roleuser fkuserroleuserId;
 @Transient
    private MultipartFile file ;

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password, String email, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<Follower> getFollowerSet() {
        return followerSet;
    }

    public void setFollowerSet(Set<Follower> followerSet) {
        this.followerSet = followerSet;
    }

    @XmlTransient
    public Set<Follower> getFollowerSet1() {
        return followerSet1;
    }

    public void setFollowerSet1(Set<Follower> followerSet1) {
        this.followerSet1 = followerSet1;
    }

    @XmlTransient
    public Set<Findmotel> getFindmotelSet() {
        return findmotelSet;
    }

    public void setFindmotelSet(Set<Findmotel> findmotelSet) {
        this.findmotelSet = findmotelSet;
    }

    @XmlTransient
    public Set<Evaluate> getEvaluateSet() {
        return evaluateSet;
    }

    public void setEvaluateSet(Set<Evaluate> evaluateSet) {
        this.evaluateSet = evaluateSet;
    }

    @XmlTransient
    public Set<Motel> getMotelSet() {
        return motelSet;
    }

    public void setMotelSet(Set<Motel> motelSet) {
        this.motelSet = motelSet;
    }

    public Roleuser getFkuserroleuserId() {
        return fkuserroleuserId;
    }

    public void setFkuserroleuserId(Roleuser fkuserroleuserId) {
        this.fkuserroleuserId = fkuserroleuserId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlphongtro.pojo.User[ id=" + id + " ]";
    }
    
}
