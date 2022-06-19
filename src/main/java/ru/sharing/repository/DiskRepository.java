package ru.sharing.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sharing.domain.Disk;

public interface DiskRepository extends CrudRepository<Disk, Long>, DiskRepositoryCustom {
}
