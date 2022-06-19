package ru.sharing.mo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class DiskMo {

	private Long id;
	private String description;
	private Date releaseDate;

}
