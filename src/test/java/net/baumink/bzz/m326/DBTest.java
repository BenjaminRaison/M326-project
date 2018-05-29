package net.baumink.bzz.m326;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DBTest {

    @Test
    void testDBConnects() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://data/CoolShoes.accdb")) {
            ResultSet result = conn.prepareStatement("SELECT * FROM Kunde;").executeQuery();
            assertFalse(result.isLast());
            assertTrue(result.next());
        }
    }

}
