package net.baumink.bzz.m326.db;

/**
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public enum Status {
    BESTELLT("Auftrag bestellt"),
    TEILAUFTRAG_VERSPÄTET("Teilauftrag verspätet"),
    AUFBEREITET("Auftrag aufbereitet"),
    VERSANDBEREIT("Auftrag versandbereit"),
    GELIEFERT("Auftrag geliefert"),
    ABGEHOLT("Auftrag abgehohlt");

    private String value;

    Status(String value) {
        this.value = value;
    }

}
