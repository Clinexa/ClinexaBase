package com.clinexa.base;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for {@link PatientFile}.
 *
 * @author Nikita S.
 * @since 0.1-dev.1
 */
public class PatientFileBuilder {

    private long uniqueID;

    private String name;
    private String lastName;
    private String secondName;
    private PatientFile.Gender gender;
    private PatientFile.Race race;

    private ZonedDateTime birthDate;
    private ZonedDateTime deathDate;

    private String occupation;

    private List<HistoryRecord> history;

    public PatientFileBuilder() {}

    public PatientFileBuilder setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
        return this;
    }

    public PatientFileBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PatientFileBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PatientFileBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public PatientFileBuilder setGender(PatientFile.Gender gender) {
        this.gender = gender;
        return this;
    }

    public PatientFileBuilder setRace(PatientFile.Race race) {
        this.race = race;
        return this;
    }

    public PatientFileBuilder setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PatientFileBuilder setDeathDate(ZonedDateTime deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    public PatientFileBuilder setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public PatientFileBuilder setHistory(List<HistoryRecord> history) {
        this.history = history;
        return this;
    }

    /**
     * Initialize an empty patient history.
     *
     * @return link to this object
     */
    public PatientFileBuilder clearHistory() {
        this.history = new ArrayList<>();
        return this;
    }

    /**
     * Add a record to patient's file.
     *
     * @return link to this object
     */
    public PatientFileBuilder addToHistory(HistoryRecord record) {
        this.history.add(record);
        return this;
    }

    /**
     * Creates new PatientFile instance with given data.
     *
     * @return new PatientFile instance.
     */
    public PatientFile build() {
        return new PatientFile(history, occupation, deathDate, birthDate, race, gender, secondName, lastName, name, uniqueID);
    }
}
