package tn.esprit.spring.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="roles")
public class Role {
	@Id
	private Long idrole;
	private String rolename;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="role")
	private Set<UserRole> userroles=new HashSet<>();
	
	@ManyToOne
	private Role role;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdrole() {
		return idrole;
	}
	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Set<UserRole> getUserroles() {
		return userroles;
	}
	public void setUserroles(Set<UserRole> userroles) {
		this.userroles = userroles;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
