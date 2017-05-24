package entity.enumiration;

/**
 * Created by Александр Горшов on 21.01.2017.
 */
public enum Role {
    ADMIN("ADMIN"), USER("USER"), BLOCKED("BLOCKED");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
