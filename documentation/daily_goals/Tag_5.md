# Tagesziele vom Tag 5

## Ziele

* Tabelle funktionstüchtig
* Dialog zum Hinzufügen von Mitarbeitern


## Tagebuch Jonas
### Tätigkeiten
Meine Aufgabe heute war wieder die Tabelle.
### Soll-Ist-Vergleich
Soll: Tabelle komplett funktionstüchtig Ist: Tabelle noch nicht ganz funktiontstüchtig.
### Erkenntnisse
Swing ist viel zu kompliziert, das nächste Mal lieber wieder JavaFx benutzen 
### Fazit
JavaFx benutzen.

## Tagebuch Benji
### Tätigkeiten
* Datenbank verbessert
    * JPA automatische Erstellung der Tabelle und Einfügen von Test-Daten
* Datenbanktests verbessert
* allgeimeines refactoring
* Jonas mit dem GUI helfen

### Soll-Ist-Vergleich
Uns ist aufgefallen, dass der Dialog nicht nötig ist.

### Erkenntnisse
JPA bzw. Hibernate verhält sich komisch beim automatischen Einfügen von Daten. Wird `javax.persistence.schema-generation.database.action` auf `create` gesetzt, versucht JPA bei jedem starten der Applikation die Tabellen nochmals zu erstellen und wirft Unmengen an Exceptions. Schlussendlich haben wir uns dazu entschieden, `drop-and-create` zu konfigurieren, da es uns das Leben vereinfacht. In der Produktion müsste dieser Parameter natürlich weggelassen werden.

### Fazit
JPA ist launisch.
