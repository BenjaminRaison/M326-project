package net.baumink.bzz.m326.db;

public enum EmployeeType {
    VERTRIEB("Vertrieb"), LAGER("LAGER"), LIEFERANT("Lieferant");

    private final String value;

    EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}