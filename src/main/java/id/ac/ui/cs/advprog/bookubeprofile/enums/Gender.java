package id.ac.ui.cs.advprog.bookubeprofile.enums;

public enum UserRole {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private final String value;
    UserRole(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        for (UserRole role:UserRole.values()) {
            if (role.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}