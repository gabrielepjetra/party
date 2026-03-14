package be.thomasmore.party.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String city;
    private String bio;
    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Party> parties;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public Collection<Party> getParties() {return parties;}

    public void setParties(Collection<Party> parties) {this.parties = parties;}

}
