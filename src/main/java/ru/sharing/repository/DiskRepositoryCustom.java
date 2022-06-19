package ru.sharing.repository;

import ru.sharing.domain.Disk;

import java.util.List;

public interface DiskRepositoryCustom {

	List<Disk> getUserDisks();

	List<Disk> getFreeDisks();

	List<Disk> getTakenDisks();

}
