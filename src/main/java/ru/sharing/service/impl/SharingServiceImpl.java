package ru.sharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sharing.domain.Disk;
import ru.sharing.exception.DiskOperationException;
import ru.sharing.mapper.DomainToMoMapper;
import ru.sharing.mo.TakenItemMo;
import ru.sharing.mo.response.GetDebtorsResponseMo;
import ru.sharing.mo.response.GetDisksResponseMo;
import ru.sharing.mo.response.GetFreeDisksResponseMo;
import ru.sharing.mo.response.GetTakenDisksResponseMo;
import ru.sharing.repository.DiskRepository;
import ru.sharing.security.domain.User;
import ru.sharing.security.service.UserService;
import ru.sharing.service.SharingService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SharingServiceImpl implements SharingService {

	private static final String DISK_NOT_FOUND_ERROR = "Диска с идентификатором %d не существует!";
	private static final String DISK_IS_BUSY_ERROR = "Данный диск уже был взят в пользование другим пользователем!";
	private static final String TAKEN_DISK_FROM_YOURSELF_ERROR = "Вы не можете взять собственный диск!";
	private static final String TAKING_DISK_MISSING_ERROR = "В ваших взятых дисках отсутствует диск в идентификатором %d! Возврат невозможен.";

	@Autowired
	private UserService userService;
	@Autowired
	private DiskRepository diskRepository;
	@Autowired
	private DomainToMoMapper domainMapper;

	@Override
	public GetDisksResponseMo getDisks() {
		return new GetDisksResponseMo(domainMapper.to(diskRepository.getUserDisks()));
	}

	@Override
	public GetFreeDisksResponseMo getFreeDisks() {
		return new GetFreeDisksResponseMo(domainMapper.to(diskRepository.getFreeDisks()));
	}

	@Override
	public GetTakenDisksResponseMo getTakenDisks() {
		return new GetTakenDisksResponseMo(domainMapper.to(diskRepository.getTakenDisks()));
	}

	@Override
	public GetDebtorsResponseMo getDebtors() {
		final List<TakenItemMo> result = diskRepository.getUserDisks()
				.stream()
				.filter(disk -> !Objects.equals(disk.getOwner(), disk.getHolder()))
				.map(disk -> domainMapper.to(disk.getHolder(), disk))
				.collect(Collectors.toList());

		return new GetDebtorsResponseMo(result);
	}

	@Override
	@Transactional
	public void takeDisk(Long diskId) {
		final Disk foundDisk = diskRepository.findById(diskId)
				.orElseThrow(() -> new DiskOperationException(String.format(DISK_NOT_FOUND_ERROR, diskId), HttpStatus.NOT_FOUND));
		final Disk checkedDisk = diskRepository.getFreeDisks()
				.stream()
				.filter(disk -> Objects.equals(disk.getId(), foundDisk.getId()))
				.findFirst()
				.orElseThrow(() -> new DiskOperationException(DISK_IS_BUSY_ERROR, HttpStatus.BAD_REQUEST));

		final User loggedUser = userService.getUser();
		if (Objects.equals(checkedDisk.getOwner(), loggedUser)) {
			throw new DiskOperationException(TAKEN_DISK_FROM_YOURSELF_ERROR, HttpStatus.BAD_REQUEST);
		}

		checkedDisk.setHolder(loggedUser);
	}

	@Override
	@Transactional
	public void returnDisk(Long diskId) {
		final Disk takenDisk = diskRepository.getTakenDisks()
				.stream()
				.filter(disk -> Objects.equals(disk.getId(), diskId))
				.findFirst()
				.orElseThrow(() -> new DiskOperationException(String.format(TAKING_DISK_MISSING_ERROR, diskId), HttpStatus.NOT_FOUND));

		takenDisk.setHolder(takenDisk.getOwner());
	}

}
