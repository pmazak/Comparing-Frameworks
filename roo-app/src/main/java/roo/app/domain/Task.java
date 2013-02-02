package roo.app.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;


@Configurable
@Entity
public class Task {

    @NotNull
    private String description;

    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dueDate;

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("DueDate: ").append(getDueDate()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public String getDescription() {
        return this.description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public Date getDueDate() {
        return this.dueDate;
    }

	public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Task attached = Task.findTask(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Task merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Task merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Task().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTasks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Task o", Long.class).getSingleResult();
    }

	public static List<Task> findAllTasks() {
        return entityManager().createQuery("SELECT o FROM Task o", Task.class).getResultList();
    }

	public static Task findTask(Long id) {
        if (id == null) return null;
        return entityManager().find(Task.class, id);
    }

	public static List<Task> findTaskEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Task o", Task.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
