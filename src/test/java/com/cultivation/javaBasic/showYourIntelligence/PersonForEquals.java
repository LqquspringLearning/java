package com.cultivation.javaBasic.showYourIntelligence;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable<PersonForEquals> {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonForEquals that = (PersonForEquals) o;
        return yearOfBirth == that.yearOfBirth &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return this.name.hashCode() + ((Short) this.yearOfBirth).hashCode();
        // --end-->
    }


    @Override
    public int compareTo(PersonForEquals o) {
        if (o == null) throw new NullPointerException();
        PersonForEquals another = o;
        int nameCompareResult = name.compareTo(another.name);
        if (nameCompareResult == 0) {
            return yearOfBirth - another.yearOfBirth > 0 ? 1 : -1;
        }
        return nameCompareResult;
    }
}
