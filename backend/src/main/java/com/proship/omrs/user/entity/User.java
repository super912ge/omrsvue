package com.proship.omrs.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.proship.omrs.contract.entity.ContractRecruiterAttributionTts;
import com.proship.omrs.gig.entity.GigTerritoryShard;
import com.proship.omrs.msd.entity.UserMsdBracketShard;
import com.proship.omrs.role.entity.Role;
import com.proship.omrs.role.entity.RoleMap;
import com.proship.omrs.role.entity.UserRole;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.todoList.entity.TodoList;

@Entity
@Component
@Access(AccessType.FIELD)
@Table(name = "system_user")
@Where(clause = "active = true")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_id_seq")
    @SequenceGenerator(
            name="user_id_seq",
            sequenceName="user_id_sequence"
    )
	private Long id;
	
	private String name;
	private String shortName;
	private String password;
	private String passwordSalt;
	private String passwordHash;
	private String email;
	private Long incentiveBracketSettingId;
	private Boolean active;
	private Long uuid;
	private String extension;
	private String fullName;
	private Long evaluationRoleId;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<GigTerritoryShard> gigTerritoryShards;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JoinColumn(name = "userId")
	private List<UserRole> userRoleList;

	@OneToMany
    @JoinColumn(name = "userId")
	private List<UserMsdBracketShard> userMsdBracketShards;

	private Boolean requisitionMailRecipient;

	@OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiterId")
	private List<ContractRecruiterAttributionTts> contractRecruiterAttributionTtsList;

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

    public List<GigTerritoryShard> getGigTerritoryShards() {
        return gigTerritoryShards;
    }

    public void setGigTerritoryShards(List<GigTerritoryShard> gigTerritoryShards) {
        this.gigTerritoryShards = gigTerritoryShards;
    }

    public List<UserMsdBracketShard> getUserMsdBracketShards() {
        return userMsdBracketShards;
    }

    public void setUserMsdBracketShards(List<UserMsdBracketShard> userMsdBracketShards) {
        this.userMsdBracketShards = userMsdBracketShards;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIncentiveBracketSettingId() {
        return incentiveBracketSettingId;
    }

    public void setIncentiveBracketSettingId(Long incentiveBracketSettingId) {
        this.incentiveBracketSettingId = incentiveBracketSettingId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getEvaluationRoleId() {
        return evaluationRoleId;
    }

    public void setEvaluationRoleId(Long evaluationRoleId) {
        this.evaluationRoleId = evaluationRoleId;
    }

    public Boolean getRequisitionMailRecipient() {
        return requisitionMailRecipient;
    }

    public void setRequisitionMailRecipient(Boolean requisitionMailRecipient) {
        this.requisitionMailRecipient = requisitionMailRecipient;
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

    public List<ContractRecruiterAttributionTts> getContractRecruiterAttributionTtsList() {
        return contractRecruiterAttributionTtsList;
    }

    public void setContractRecruiterAttributionTtsList(List<ContractRecruiterAttributionTts> contractRecruiterAttributionTtsList) {
        this.contractRecruiterAttributionTtsList = contractRecruiterAttributionTtsList;
    }
}
