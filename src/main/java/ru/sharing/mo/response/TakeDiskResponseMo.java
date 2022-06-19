package ru.sharing.mo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TakeDiskResponseMo {

	private Long diskId;
	private String message;

}
