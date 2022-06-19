package ru.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sharing.controller.urls.ControllerURLs;
import ru.sharing.mo.request.ReturnDiskRequestMo;
import ru.sharing.mo.response.GetDebtorsResponseMo;
import ru.sharing.mo.response.GetDisksResponseMo;
import ru.sharing.mo.response.GetFreeDisksResponseMo;
import ru.sharing.mo.response.GetTakenDisksResponseMo;
import ru.sharing.mo.request.TakeDiskRequestMo;
import ru.sharing.service.SharingService;

@RestController
public class SharingController {

	@Autowired
	private SharingService sharingService;

	@GetMapping(ControllerURLs.DISKS_URL)
	public GetDisksResponseMo getDisks() {
		return sharingService.getDisks();
	}

	@GetMapping(ControllerURLs.FREE_DISKS_URL)
	public GetFreeDisksResponseMo getFreeDisks() {
		return sharingService.getFreeDisks();
	}

	@GetMapping(ControllerURLs.TAKEN_DISKS_URL)
	public GetTakenDisksResponseMo getTakenDisks() {
		return sharingService.getTakenDisks();
	}

	@GetMapping(ControllerURLs.DEBTORS_URL)
	public GetDebtorsResponseMo getDebtors() {
		return sharingService.getDebtors();
	}

	@PutMapping(ControllerURLs.TAKE_DISK_URL)
	public ResponseEntity<?> takeDisk(@RequestBody TakeDiskRequestMo requestMo) {
		sharingService.takeDisk(requestMo.getDiskId());
		return ResponseEntity.ok()
				.build();
	}

	@PutMapping(ControllerURLs.RETURN_DISK_URL)
	public ResponseEntity<?> returnDisk(@RequestBody ReturnDiskRequestMo requestMo) {
		sharingService.returnDisk(requestMo.getDiskId());
		return ResponseEntity.ok()
				.build();
	}

}
