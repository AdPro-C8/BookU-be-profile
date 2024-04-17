package id.ac.ui.cs.advprog.bookubeprofile.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private final String value;
    Gender(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        for (Gender role:Gender.values()) {
            if (role.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}