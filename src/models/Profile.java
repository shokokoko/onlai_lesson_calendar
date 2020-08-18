package models;

import java.sql.Timestamp;

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
import javax.persistence.Table;

@Table(name = "profiles")
@NamedQueries({
    @NamedQuery(
            name = "getAllProfiles",
            query = "SELECT p FROM Profile AS p ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "getProfilesCount",
            query = "SELECT COUNT(p) FROM Profile AS p"
            ),
    @NamedQuery(
            name = "getMyAllProfiles",
            query = "SELECT p FROM Profile AS p WHERE p.instructor = :instructor ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "getMyProfilesCount",
            query = "SELECT COUNT(p) FROM Profile AS p WHERE p.instructor = :instructor"
            ),

})
@Entity
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    //@Column(name = "thumbnail", nullable = false)
    //private String thumbnail;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "mainprogram", length = 255, nullable = false)
    private String mainprogram;

    @Column(name = "language", length = 255, nullable = false)
    private String language;

    @Lob
    @Column(name = "qualifications", nullable = false)
    private String qualifications;

  //@Column(name = "photo", nullable = false)
    //private String photo;

    @Lob
    @Column(name = "snsblog", nullable = false)
    private String snsblog;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /* public String getTitle() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    } */

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMainprogram() {
        return mainprogram;
    }

    public void setMainprogram(String mainprogram) {
        this.mainprogram = mainprogram;
    }

    public String getLanguage() {
        return language ;
    }

    public void setLanguage (String language) {
        this.language = language;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    /* public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    } */

    public String getSnsblog() {
        return snsblog;
    }

    public void setSnsblog(String snsblog) {
        this.snsblog = snsblog;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}