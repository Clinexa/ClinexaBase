/*
 * This file is part of Clinexa Base.
 *
 * Clinexa Base is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Clinexa Base is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with Clinexa. If not, see <https://www.gnu.org/licenses/>.
 */

package com.clinexa.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * Class for storing information about a patient.
 *
 * @author Nikita S.
 * @since 0.1-dev.1
 */
public class PatientFile {

    public enum Gender {
        MALE,
        FEMALE,
        UNDEFINED
    }

    public enum Race {
        WHITE,
        AFRICAN_AMERICAN,
        NATIVE_AFRICAN,
        AMERICAN_INDIAN,
        ASIAN,
        OTHER
    }

    private final long uniqueID;

    @NotNull private final String name;
    @NotNull private final String lastName;
    @Nullable private final String secondName;
    @NotNull private Gender gender;
    @NotNull private final Race race;

    @NotNull private final ZonedDateTime birthDate;
    @Nullable private ZonedDateTime deathDate;
    private final int age;

    @Nullable private String occupation;

    @NotNull private final List<HistoryRecord> history;

    private boolean changed = false;

    /**
     * Creates new PatientFile from its data. Should only be used in
     * PatientFileBuilder.
     *
     * @param history history file of the patient, can't be null.
     * @param occupation occupation of the patient or null if none.
     * @param deathDate date of the patient's death or null if patient's alive.
     * @param birthDate date of birth of the patient.
     * @param race race of the patient.
     * @param gender gender of the patient.
     * @param lastName last name or surname of the patient.
     * @param secondName second name of the patient of null if none.
     * @param name first name of the patient.
     * @param ID unique ID for the patient, may be ID field from DB.
     */
    protected PatientFile(@NotNull List<HistoryRecord> history, @Nullable String occupation, @Nullable ZonedDateTime deathDate, @NotNull ZonedDateTime birthDate, @NotNull Race race, @NotNull Gender gender, @Nullable String secondName, @NotNull String lastName, @NotNull String name, long ID) {
        this.history = history;
        this.occupation = occupation;
        this.deathDate = deathDate;
        this.birthDate = birthDate;

        this.age = Math.toIntExact(ChronoUnit.YEARS.between(birthDate,
                Objects.requireNonNullElseGet(deathDate, () -> LocalDate.now(ZoneOffset.UTC))));

        this.race = race;
        this.gender = gender;
        this.lastName = lastName;
        this.secondName = secondName;
        this.name = name;
        this.uniqueID = ID;
    }

    public long getUniqueID() {
        return uniqueID;
    }

    public @NotNull String getName() {
        return name;
    }

    public @Nullable String getSecondName() {
        return secondName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public @NotNull Gender getGender() {
        return gender;
    }

    public @NotNull Race getRace() {
        return race;
    }

    public @NotNull ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public @Nullable ZonedDateTime getDeathDate() {
        return deathDate;
    }

    public int getAge() {
        return age;
    }

    public @Nullable String getOccupation() {
        return occupation;
    }

    public @NotNull List<HistoryRecord> getHistory() {
        return history;
    }

    /**
     * Add's a record to history. IT IS ONLY SAVED IN THIS
     * OBJECT INSTANCE!
     *
     * @param record history record to add.
     */
    public void addToHistory(HistoryRecord record) {
        changed = true;
        this.history.add(record);
    }

    public void setGender(@NotNull Gender gender) {
        changed = true;
        this.gender = gender;
    }

    public void setDeathDate(@Nullable ZonedDateTime deathDate) {
        changed = true;
        this.deathDate = deathDate;
    }

    public void setOccupation(@Nullable String occupation) {
        changed = true;
        this.occupation = occupation;
    }

    /**
     * Returns true if patient's data has been updated since last call
     * to {@link #resetChanged()}.
     *
     * @return true if patient's data has been updated.
     */
    public boolean isChanged() {
        return changed;
    }

    public void resetChanged() {
        changed = false;
    }

    /**
     * Provides short String description of the patient's file.
     * Implementation is not defined.
     *
     * @return short String description of the patient's file.
     */
    @Override
    public String toString() {
        return "PatientFile{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uniqueID=" + uniqueID +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    /**
     * Compares files by the id of the patient.
     *
     * @param o object to compare this object with.
     * @return true if IDs of both files are equal, false otherwise or if null.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PatientFile that)) return false;
        return uniqueID == that.uniqueID;
    }

    /**
     * Generates a hash code based on user's unique ID.
     *
     * @return hash code of the object.
     */
    @Override
    public int hashCode() {
        return Long.hashCode(uniqueID);
    }
}
