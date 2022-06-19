package ru.sharing.mo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.sharing.mo.DiskMo;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetFreeDisksResponseMo {

	private List<DiskMo> disks;

}
