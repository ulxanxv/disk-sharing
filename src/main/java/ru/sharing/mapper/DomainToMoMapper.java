package ru.sharing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sharing.domain.Disk;
import ru.sharing.mo.DiskMo;
import ru.sharing.mo.TakenItemMo;
import ru.sharing.mo.UserMo;
import ru.sharing.security.domain.User;

import java.util.List;

@Mapper
public interface DomainToMoMapper {

	UserMo to(User user);

	DiskMo to(Disk disk);

	List<DiskMo> to(List<Disk> disks);

	@Mapping(source = "user", target = "debtor")
	TakenItemMo to(User user, Disk disk);

}
