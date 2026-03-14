package be.thomasmore.party.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String artistName;
    @Column(length = 1000)
    private String bio;
    private String genre;
    private String linkMoreInfo;
    @Column(length = 500)
    private String portfolio;
    private String imageUrl;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    private Collection<Party> parties;

    public Collection<Party> getParties() {
        return parties;
    }

    public void setParties(Collection<Party> parties) {
        this.parties = parties;
    }

    public Artist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {this.artistName = artistName;}

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
