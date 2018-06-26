package net.baumink.bzz.m326.db;

/**
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public enum Status {
    BESTELLT("Auftrag bestellt"), AUFBEREITEN("Auftrag aufbereiten"),
    TEILAUFTRAG_VERSPÄTET("Teilauftrag verspätet"),
    VERSANDBEREIT("Auftrag versandbereit"),
    ABGEHOLT("Auftrag abgehohlt"),
    GELIEFERT("Auftrag geliefert");

    private String value;

    Status(String value) {
        this.value = value;
    }

}
