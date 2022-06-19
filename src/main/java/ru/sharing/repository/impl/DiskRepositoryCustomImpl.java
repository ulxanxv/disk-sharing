package ru.sharing.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sharing.domain.Disk;
import ru.sharing.repository.DiskRepositoryCustom;
import ru.sharing.security.domain.User;
import ru.sharing.security.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DiskRepositoryCustomImpl implements DiskRepositoryCustom {

	private static final String OWNER = "owner";
	private static final String HOLDER = "holder";

	@Autowired
	private UserService userService;
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Disk> getUserDisks() {
		return userService.getUser()
				.getDisks();
	}

	@Override
	public List<Disk> getFreeDisks() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Disk> query = builder.createQuery(Disk.class);

		Root<Disk> root = query.from(Disk.class);
		query
				.select(root)
				.where(builder.equal(root.get(OWNER), root.get(HOLDER)));

		return entityManager.createQuery(query)
				.getResultList();
	}

	@Override
	public List<Disk> getTakenDisks() {
		final User loggedUser = userService.getUser();
		return loggedUser.getCurrentDisks()
				.stream()
				.filter(disk -> !Objects.equals(disk.getOwner(), loggedUser))
				.collect(Collectors.toList());
	}

}
