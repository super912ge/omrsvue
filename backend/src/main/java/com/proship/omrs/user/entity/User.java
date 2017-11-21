package com.proship.omrs.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.proship.omrs.role.entity.Role;
import com.proship.omrs.role.entity.RoleMap;
import com.proship.omrs.role.entity.UserRole;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.todoList.entity.TodoList;

@Entity
@Component
@Access(AccessType.FIELD)
@Table(name = "system_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_id_seq")
    @SequenceGenerator(
            name="user_id_seq",
            sequenceName="user_id_sequence"
    )
	private Long id;
	
	private String name;
	private String short_name;

	private String password;

	private String password_salt;
	private String password_hash;
	private String email;
	private Long  incentive_bracket_setting_id;
	private Boolean active;
	private Long uuid;
	private String extension;
	private String full_name;
	private Long evaluation_role_id;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JoinColumn(name = "userId")
	private List<UserRole> userRoleList;

	private Boolean requisition_mail_recipient;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TodoList> todoList;

	@Transient
	private List<Role> roleList = new ArrayList<>();


	public User(){};
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getPassword_salt() {
		return password_salt;
	}
	public void setPassword_salt(String password_salt) {
		this.password_salt = password_salt;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIncentive_bracket_setting_id() {
		return incentive_bracket_setting_id;
	}
	public void setIncentive_bracket_setting_id(Long incentive_bracket_setting_id) {
		this.incentive_bracket_setting_id = incentive_bracket_setting_id;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public Long getEvaluation_role_id() {
		return evaluation_role_id;
	}
	public void setEvaluation_role_id(Long evaluation_role_id) {
		this.evaluation_role_id = evaluation_role_id;
	}
	public Boolean getRequisition_mail_recipient() {
		return requisition_mail_recipient;
	}
	public void setRequisition_mail_recipient(Boolean requisition_mail_recipient) {
		this.requisition_mail_recipient = requisition_mail_recipient;
	}

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;

    }

    public List<TodoList> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoList> todoList) {
        this.todoList = todoList;
    }

    public List<Role> getRoleList() {

	    if(this.id!=null&&this.userRoleList!=null&&!this.userRoleList.isEmpty()){
	        updateRoleListByUserRoleList();
        }
	    return roleList;
	}

	public void setRoleList(List<Role> roleList) {


	    this.roleList = roleList;

	}

	public void updateRoleListByUserRoleList(){

		this.roleList = new ArrayList<>();

		if (this.userRoleList!=null && !this.userRoleList.isEmpty())

			for(UserRole ur : this.userRoleList){

				Role role = RoleMap.getRoleType(ur.getRoleId());
				roleList.add(role);
			}
	}
	public void updateUserRoleListByRoleList(){
		this.userRoleList = roleList.stream().map(role -> new UserRole(
				this.getId(),role.getId())).collect(Collectors.toList());
	}
	public User(Long id){
		this.id = id;
	}
}
