package co.com.security.app.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "usuario_roles", //nombre tabla con PKey compuesta
		joinColumns = @JoinColumn(name = "usuario_id"), //Pk de esta clase (id)
		inverseJoinColumns = @JoinColumn(name = "role_id"), //Pk de la otra clase (id)
		uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id" })) //no se puede repitir la id compuesta
	private List<Role> role;

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

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	private static final long serialVersionUID = 1L;

}
