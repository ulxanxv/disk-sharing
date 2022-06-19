package ru.sharing.mo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.sharing.mo.TakenItemMo;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetDebtorsResponseMo {

	private List<TakenItemMo> debtors;

}
