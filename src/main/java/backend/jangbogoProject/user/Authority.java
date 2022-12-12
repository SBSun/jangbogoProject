package backend.jangbogoProject.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {
    ADMIN("ROLE_ADMIN,ROLE_USER"),
    USER("ROLE_USER");

    private String value;
}
