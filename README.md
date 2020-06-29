# PasswordSafe
Partnerarbeit von Jeremy und Joel für das Modul 183

---

## Funktionalität
Diese Spring Boot Applikation ist zuständig zur sicheren Aufbewahrung und Verwaltung von Passwörtern. Der Benutzer hat die Möglichkeit sich mit seinem Usernamen und Masterpassword einzuloggen und kann danach Passworteinträge hinzufügen, bearbeiten und löschen. Mit einem Knopfdruck kann der Benutzer sich einloggen und seine Passwörter werden verschlüsselt aufbewahrt.

## Bedienung
Für Sie Herr Lanza haben wir einen eigenen Account erstellt. Ihr Benutzername lautet **RinaldoChef** und ihr Passwort lautet **123456**. Sie erreichen unsere Applikation [hier](localhost:8080/login), wenn der Spring Boot Server gestartet wurde. 

---

## Security
### Hashing
Das Masterpassword des Benutzers wird mit dem SHA-256 Algorithmus gehasht und in der Datenbank abgespeichert. Wenn der Benutzer versucht sich einzuloggen, wird die Eingabe gehasht und mit dem Eintrag in der Datenbank verglichen. Der Hash des Benutzers wird nirgends im Programm abgespeichert, lediglich in der Datenbank. 

### Verschlüsselung
Die Applikation verwendet Symmetrische Verschlüsselung zur sicheren Speicherung der Daten. Bei der Symmetrischen Verschlüsselung können Daten mit demselben Schlüssel verschlüsselt und entschlüsselt werden.
Wenn der Benutzer sich erfolgreich einloggt, dann werden seine verschlüsselten Einträge aus der Datenbank gelesen und mit dem Key entschlüsselt. Beim ausloggen wird derselbe Key verwendet, um die Daten verschlüsselt abzuspeichern. Die Liste von Entry-Objekten werden dazu erst zu einem JSON-String serialisiert.
Das Handling vom Key ist Security-technisch wichtig, denn wenn man diesen falsch handelt bring die Verschlüsselung nichts. Der Key wird beim Einloggen generiert, in dem das bereits gehashte Passwort nochmals mit dem MD-5 Algorithmus gehasht wird. Normalerweise sollte man den Algorithmus nicht brauchen, doch in diesem Falle ist es genug, denn wir machen diese Operation um die nötige Key-Länge (16 Bytes) zu erhalten. Der erhaltene Key wird im Session-Scoped Service gespeichert.

### Session-Handling
Unser Session-handling ist sehr simpel. Die Benutzer Id wird im Session-Scoped Service gespeichert. Wenn versucht wird, die Passwortverwaltung zu öffnen, dann wird überprüft ob es einen eingeloggten Benutzer gibt. So verhindert man nicht-authentisierten Zugriff. 
Validierung der Eingaben
Beim Erstellen und Bearbeiten von Einträgen findet eine Server-Seitige Validierung statt. Die Felder URL und Notizen sind optional und werden nicht validiert. Der Benutzername hingegen darf nicht leer sein. Das Passwort muss zwischen 4 und 20 Zeichen liegen und mindestens einen Gross –und Kleinbuchstaben als auch eine Zahl aufweisen. Die E-Mail muss eine allgemein anerkannte Struktur haben. Die Meldung bei fehlerhaften Eingaben wird aufgrund eines unbekannten Fehlers leider nicht ausgegeben. 

--- 

## Weitere Schritte
### Passwort vergessen
Im Fall, dass ein Benutzen sein Passwort vergisst, kann er auf «Passwort vergessen» klicken. Dann würde auf die hinterlegte Email eine Art Tan-Code gesendet werden. Der Benutzer müsste den Code dann auf der Webapplikation eingeben, um sein Neues Passwort wählen zu können. So müssten wir nicht das Passwort unverschlüsselt per Mail versenden und die Daten bleiben sicher. 
### Passwort Generator
Auch könnten wir uns vorstellen einen Passwortgenerator zu implementieren. So kann gewährleistet werden, dass der User immer sichere Passwörter verwenden kann.

### Benutzer registrieren
In Zukunft wird es auch möglich sein, neue Benutzer zu registrieren. Dies hat den Vorteil, mehrere Benutzer gleichzeitig zu Verwalten. Jeder dieser User kann seine eigenen Einträge erstellen, sowie bearbeiten und löschen.

--- 

## Kurze Reflexion
Ich würde sagen es ist uns gut gelungen, diese Arbeit umzusetzen. Wir hatten die meisten Konzepte in der Schule gelernt, aber das alles korrekt und sicher umzusetzen in wenig Zeit war herausfordernd. Wir sind uns bewusst, dass manche Stellen, genauergesagt das Session-Handling, Optimerungspotential haben. Wir sind zufrieden mit dem Resultat. 
