package ru.sharing.mo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TakenItemMo {

	private UserMo debtor;
	private DiskMo disk;

}
