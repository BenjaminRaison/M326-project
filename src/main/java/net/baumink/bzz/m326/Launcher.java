package net.baumink.bzz.m326;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.EmployeeType;
import net.baumink.bzz.m326.db.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import net.baumink.bzz.m326.db.pojo.Client;
import net.baumink.bzz.m326.db.pojo.Employee;
import net.baumink.bzz.m326.db.pojo.Item;
import net.baumink.bzz.m326.view.MainWindow;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

/**
 * The type Launcher.
 *  @author Benjamin Raison, Jonas Gredig
 *  @version 1.0
 */
public class Launcher {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        setupErrorHandling();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.err.println("Failed to set look and feel!" + e.getMessage());
        }
//        insertTestData();
        new MainWindow();
    }

    private static void insertTestData() { // TODO: Remove test data
        Client client = new Client("Raison", "Benjamin", "some place", "SJBM",
                "MyTown", "benji@raison.local", "imagethisisahash");
        EntityManager entityManager = DBConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        Employee employee = new Employee("Jonas", "Gredig", EmployeeType.LIEFERANT);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        Item item = new Item("Test item", "Superduper testitem 2.0 with AI", new BigDecimal("123.45"));
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        CSOrder order = new CSOrder("123", client, employee, ZonedDateTime.now(), ZonedDateTime.now(), Status.AUFBEREITEN, Collections.singletonList(item));
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    private static void setupErrorHandling() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getCanonicalName();
            }
            JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        });
    }
}
