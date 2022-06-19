package ru.sharing.service;

import ru.sharing.mo.response.GetDebtorsResponseMo;
import ru.sharing.mo.response.GetDisksResponseMo;
import ru.sharing.mo.response.GetFreeDisksResponseMo;
import ru.sharing.mo.response.GetTakenDisksResponseMo;

public interface SharingService {

	GetDisksResponseMo getDisks();

	GetFreeDisksResponseMo getFreeDisks();

	GetTakenDisksResponseMo getTakenDisks();

	GetDebtorsResponseMo getDebtors();

	void takeDisk(Long diskId);

	void returnDisk(Long diskId);

}
