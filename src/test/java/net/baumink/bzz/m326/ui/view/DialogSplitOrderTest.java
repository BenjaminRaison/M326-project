package net.baumink.bzz.m326.ui.view;

import net.baumink.bzz.m326.db.DBConnection;
import net.baumink.bzz.m326.db.enums.Status;
import net.baumink.bzz.m326.db.pojo.CSOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("it")
@DisabledIfEnvironmentVariable(named = "travis", matches = "ci")
class DialogSplitOrderTest {

    private static DialogSplitOrder dialog;

    private static Stream<Arguments> next_order_number_arguments() {
        return Stream.of(
                Arguments.of("123", "123A"),
                Arguments.of("123B", "123A"),
                Arguments.of("12J", "12A"),
                Arguments.of("1", "1A"),
                Arguments.of("123456Y", "123456A")
        );
    }

    private static Stream<Arguments> next_order_number_arguments_fail() {
        return Stream.of(
                Arguments.of("A"),
                Arguments.of("123Z"),
                Arguments.of("A123A"),
                Arguments.of("A123"),
                Arguments.of("123ä"),
                Arguments.of(""),
                Arguments.of(new Object[]{null})
        );
    }

    @BeforeAll
    static void init() {
        if (System.getenv("travis").equals("ci")) return;
        dialog = new DialogSplitOrder(new CSOrder("1", null, null, null,
                null, Status.BESTELLT, new ArrayList<>()), null);
        dialog.dispose();
        DBConnection.setIsTest();

        DBConnection.getEntityManager().getTransaction().begin();
        DBConnection.getEntityManager().createQuery("delete from CSOrder o").executeUpdate();
        DBConnection.getEntityManager().getTransaction().commit();
    }

    @ParameterizedTest
    @MethodSource("next_order_number_arguments")
    void test_next_order_number(String input, String result) {
        assertEquals(result, dialog.getNextOrderNumber(input));
    }

    @ParameterizedTest
    @MethodSource("next_order_number_arguments_fail")
    void test_next_order_number_fail(String input) {
        assertThrows(IllegalArgumentException.class, () -> dialog.getNextOrderNumber(input));
    }

}
