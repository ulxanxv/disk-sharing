package ru.sharing.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sharing.security.domain.User;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "DISK")
public class Disk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1024)
	private String description;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date releaseDate;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User owner;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User holder;

}
