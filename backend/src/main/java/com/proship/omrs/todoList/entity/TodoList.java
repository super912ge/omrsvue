package com.proship.omrs.todoList.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;

@Entity
@Table(name="to_do_list")
public class TodoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="todoList_seq")
	@SequenceGenerator(
			name="todoList_seq",
			sequenceName="to_do_list_id_sequence"
		)
	Long id;

	@NotNull
	String text;

	@Column(insertable = false)
	Boolean done;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="user_id")
	//@JsonIgnore
	@JsonSerialize(using = UserSerializer.class)
	User user;

	@NotNull
	Long priorityId;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	Timestamp dueDate;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(insertable = false)
	Timestamp createDate;

	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	Timestamp modificationDate;

	@NotNull
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	

}
