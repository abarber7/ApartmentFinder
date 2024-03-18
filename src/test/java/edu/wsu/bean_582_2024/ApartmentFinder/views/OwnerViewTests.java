package edu.wsu.bean_582_2024.ApartmentFinder.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.vaadin.flow.data.provider.ListDataProvider;
import edu.wsu.bean_582_2024.ApartmentFinder.model.Role;
import edu.wsu.bean_582_2024.ApartmentFinder.model.User;
import edu.wsu.bean_582_2024.ApartmentFinder.service.SecurityService;
import edu.wsu.bean_582_2024.ApartmentFinder.service.UnitService;
import edu.wsu.bean_582_2024.ApartmentFinder.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.vaadin.flow.component.grid.Grid;

import edu.wsu.bean_582_2024.ApartmentFinder.model.Unit;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

//Integration Testing

@ExtendWith(MockitoExtension.class)
public class OwnerViewTests {

	static {
		// Prevent Vaadin Development mode to launch browser window
		System.setProperty("vaadin.launch-browser", "false");
	}

	private OwnerView ownerView;
	private Unit unit1;
	private Unit unit2;
	private Unit unit3;

	@Mock
	UserService userService;

	@Mock
	SecurityService securityService;

	@Mock
	UnitService unitService;

	@BeforeEach
	public void setup() {
		userService = Mockito.mock(UserService.class);
		unitService = Mockito.mock(UnitService.class);
		securityService = Mockito.mock(SecurityService.class);

		User user1 = new User("user1", "password1", Role.ADMIN);
		user1.setId(1L);
		User user2 = new User("user2", "password2", Role.OWNER);
		user2.setId(2L);
		User user3 = new User("user3", "password3", Role.USER);
		user3.setId(3L);

		List<User> users = List.of(user1, user2, user3);

		unit1 = new Unit("123 876th Drive", 5, 3.75, "Living room #1", "Kitchen #1", true, user2);
		unit2 = new Unit("1234 876th Drive", 4, 2.75, "Living room #2", "Kitchen #2", true, user2);
		unit3 = new Unit("12345 876th Drive", 3, 1.75, "Living room #3", "Kitchen #3", true, user2);
		List<Unit> units = List.of(unit1, unit2, unit3);

		when(userService.getAllUsers()).thenReturn(users);
		when(userService.findUsers(anyString())).thenReturn(users);
		when(userService.findUserByUsername(anyString())).thenReturn(Optional.of(user2));
		when(securityService.getAuthenticatedUser()).thenReturn(Optional.of(user2));
		when(unitService.getAllUnits(anyBoolean())).thenReturn(units);
		when(unitService.getUnitCount()).thenReturn(3L);
		when(unitService.getUsersUnits(any())).thenReturn(units);
		ownerView = new OwnerView(unitService, securityService, userService);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void formShownWhenUnitSelectedTest() {
		Grid<Unit> grid = ownerView.getGrid();
		ListDataProvider<Unit> ldp = (ListDataProvider<Unit>) grid.getDataProvider();
		assertEquals(3, unitService.getAllUnits(true).stream().count());
		assertEquals(3, ldp.getItems().stream().count());
		Unit firstUnit = getFirstItem(grid);

		OwnerForm form = ownerView.getOwnerForm();
		assertFalse(form.isVisible());
		grid.asSingleSelect().setValue(firstUnit);

		assertTrue(form.isVisible());
		assertEquals(firstUnit.getAddress(), form.address.getValue());
		form.cancel.click();
		assertFalse(form.isVisible());
	}

	@Test
	public void unitEditedAndSavedTst() {
		Grid<Unit> grid = ownerView.getGrid();
		Unit firstUnit = getFirstItem(grid);

		OwnerForm form = ownerView.getOwnerForm();
		assertFalse(form.isVisible());
		grid.asSingleSelect().setValue(firstUnit);

		assertTrue(form.isVisible());
		assertEquals(firstUnit.getAddress(), form.address.getValue());

		form.address.setValue("THIS IS A CHANGED ADDRESS");
		form.save.click();

		List<Unit> units = grid.getListDataView().getItems().toList();
		Optional<Unit> unitChanged1 = units.stream().findFirst();
		assertNotNull(unitChanged1);

		assertFalse(form.isVisible());
		assertEquals("THIS IS A CHANGED ADDRESS", unitChanged1.get().getAddress());
	}

	@Test
	public void unitEditDeletedTest() {
		Grid<Unit> grid = ownerView.getGrid();
		Unit firstUnit = getFirstItem(grid);

		OwnerForm form = ownerView.getOwnerForm();
		System.out.println(firstUnit.getAddress());
		assertFalse(form.isVisible());
		grid.asSingleSelect().setValue(firstUnit);

		assertTrue(form.isVisible());
		form.delete.click();
		assertFalse(form.isVisible());

		assertEquals(2, grid.getListDataView().getItems().count());
	}

	private Unit getFirstItem(Grid<Unit> grid) throws NullPointerException {
		return Objects.requireNonNull(grid).getListDataView().getItems().iterator().next();
	}
}
