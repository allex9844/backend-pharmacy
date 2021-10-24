package ro.kronsoft.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
	private static final long serialVersionUID = -7604503412959777306L;

	@Column(name = "USERNAME", nullable = false, length = 50, unique = true)
	private String username;

	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 80)
	private String password;

	@Column(name = "USER_AUTHORITY", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserAuthority authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

}
