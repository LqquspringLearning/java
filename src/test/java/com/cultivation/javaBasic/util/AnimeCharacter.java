package com.cultivation.javaBasic.util;

public class AnimeCharacter {
    private final String name;
    private final String time;
    public AnimeCharacter(String name) {
        if (name == null) throw new IllegalArgumentException("Name is mandatory.");
        this.name = name;
        this.time="";
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!obj.getClass().equals(AnimeCharacter.class)) return false;
        AnimeCharacter other = (AnimeCharacter) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
