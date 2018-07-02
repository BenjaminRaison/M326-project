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
#### Das hat mich gefreut:
Ich kann sehr viel über Swing lernen, auch wenn das meiste mich nur aufregt, da es mit JavaFx viel einfacher funktionieren würde...

#### Das hat mich geärgert:
Swing ist sehr mühsam was GUIs bauen anbelangt. Nächstes Mal lieber JavaFx benutzen.

## Tagebuch Benji
### Tätigkeiten
* Datenbank verbessert
    * JPA automatische Erstellung der Tabelle und Einfügen von Test-Daten
* Datenbanktests verbessert
* allgeimeines refactoring
* Jonas mit dem GUI helfen

### Soll-Ist-Vergleich
Uns ist aufgefallen, dass der Dialog nicht nötig ist. Der Rest ist umgesetzt worden.

### Erkenntnisse
JPA bzw. Hibernate verhält sich komisch beim automatischen Einfügen von Daten. Wird `javax.persistence.schema-generation.database.action` auf `create` gesetzt, versucht JPA bei jedem starten der Applikation die Tabellen nochmals zu erstellen und wirft Unmengen an Exceptions. Schlussendlich haben wir uns dazu entschieden, `drop-and-create` zu konfigurieren, da es uns das Leben vereinfacht. In der Produktion müsste dieser Parameter natürlich weggelassen werden.
JPA ist launisch.

### Fazit
#### Das hat mich gefreut
Nichts, das meiste hat nicht funktioniert.
#### Das hat mich geärgert
Ich hatte schon wieder komplexe/verwirrende Probleme mit JPA, welche ich erst nach Stunden lösen konnte.
