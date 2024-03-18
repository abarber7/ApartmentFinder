package edu.wsu.bean_582_2024.ApartmentFinder.views;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wsu.bean_582_2024.ApartmentFinder.model.Role;
import edu.wsu.bean_582_2024.ApartmentFinder.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class AdminFormTests {

    private User adminUser;
    private User ownerUser;
    private User normalUser;

    @BeforeEach
    public void populateUser() {
        adminUser = new User();
        adminUser.setEnabled(true);
        adminUser.setPassword("adminPassword");
        adminUser.setRole(Role.ADMIN);
        adminUser.setUsername("myAdminUserName");

        ownerUser = new User("myOwnerUserName", "ownerPassword", Role.OWNER);
        ownerUser.setEnabled(false);

        normalUser = new User("myNormalUserName", "userPasword", Role.USER);
        normalUser.setEnabled(true);
    }
    @Test
    public void whenUserIsSavedTest() {
        AdminForm adminForm = new AdminForm();
        AtomicReference<User> savedUserRef = new AtomicReference<>(null);
        adminForm.addSaveListener(e -> savedUserRef.set(e.getUser()));
        adminForm.setUser(adminUser);

        adminForm.save.click();

        User savedUser = savedUserRef.get();

        assertEquals(adminUser.getUsername(), savedUser.getUsername());
        assertEquals(true, savedUser.getEnabled());
        assertEquals(Role.ADMIN, savedUser.getRole());
        assertEquals(adminUser.getPassword(), savedUser.getPassword());
    }

    @Test
    public void whenUserIsValidatedAndSavedTest() {
        AdminForm adminForm = new AdminForm();
        AtomicReference<User> savedUserRef = new AtomicReference<>(null);
        adminForm.addSaveListener(e -> savedUserRef.set(e.getUser()));
        adminForm.setUser(adminUser);
        adminForm.validateAndSave();

        User savedUser = savedUserRef.get();

        assertEquals(adminUser.getUsername(), savedUser.getUsername());
        assertEquals(true, savedUser.getEnabled());
        assertEquals(Role.ADMIN, savedUser.getRole());
        assertEquals(adminUser.getPassword(), savedUser.getPassword());
    }

    @Test
    public void whenUserIsDeletedTest() {
        AdminForm adminForm = new AdminForm();
        AtomicReference<User> deletedUserRef = new AtomicReference<>(null);
        adminForm.addDeleteListener(e -> deletedUserRef.set(e.getUser()));
        adminForm.setUser(ownerUser);

        adminForm.delete.click();

        User deletedUser = deletedUserRef.get();

        assertEquals(ownerUser.getPassword(), deletedUser.getPassword());
        assertEquals(false, deletedUser.getEnabled());
        assertEquals(Role.OWNER, deletedUser.getRole());
        assertEquals(ownerUser.getUsername(), deletedUser.getUsername());
    }

    @Test
    public void whenUserIsCancelledTest() {
        AdminForm adminForm = new AdminForm();
        AtomicReference<User> cancelledUserRef = new AtomicReference<>(null);
        adminForm.addCloseListener(e -> cancelledUserRef.set(e.getUser()));
        adminForm.setUser(normalUser);
        adminForm.cancel.click();

        User cancelledUser = cancelledUserRef.get();
        assertNull(cancelledUser);
    }
}
