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

@Table(name = "lessons")
@NamedQueries({
    @NamedQuery(
            name = "getAllLessons",
            query = "SELECT l FROM Lesson AS l ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getLessonsCount",
            query = "SELECT COUNT(l) FROM Lesson AS l"
            ),
    @NamedQuery(
            name = "getMyAllLessons",
            query = "SELECT l FROM Lesson AS l WHERE l.instructor = :instructor ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getMyLessonsCount",
            query = "SELECT COUNT(l) FROM Lesson AS l WHERE l.instructor = :instructor"
            )
})
@Entity
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @Column(name = "thumbnail", length = 260)
    private String thumbnail;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "required_time", nullable = false)
    private Integer required_time;

    @Column(name = "application", nullable = false)
    private String application;

    @Column(name = "charge")
    private Integer charge;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "notes", nullable = false)
    private String notes;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "image")
    private String image;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRequired_time() {
        return required_time;
    }

    public void setRequired_time(Integer required_time) {
        this.required_time = required_time;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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