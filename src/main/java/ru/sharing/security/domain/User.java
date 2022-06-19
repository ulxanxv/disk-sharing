package ru.sharing.security.domain;

import lombok.Getter;
import lombok.Setter;
import ru.sharing.domain.Disk;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "USR", indexes = @Index(columnList = "login"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 16, unique = true, nullable = false)
	private String login;

	@Column(length = 1024, nullable = false)
	private String password;

	@OneToMany(mappedBy = "owner")
	private List<Disk> disks;

	@OneToMany(mappedBy = "holder")
	private List<Disk> currentDisks;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return
				Objects.equals(id, user.id) &&
				Objects.equals(login, user.login) &&
				Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		int hash = id.hashCode();
		hash = 31 * hash + login.hashCode();
		hash = 31 * hash + password.hashCode();
		return hash;
	}

}
