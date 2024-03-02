package at.spengergasse.sst.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static at.spengergasse.sst.foundation.AssertUtil.isValidEmail;
import static at.spengergasse.sst.foundation.EntityUtil.generateUUIDv4;

@Getter
@ToString(callSuper = true)
@Document(collection = "user")
public abstract  class User extends BaseEntity<String> {

    @Indexed(unique = true)
    private String email;

    // @JsonIgnore
    private String password;

    private Role role;


    // ctor --------------------------------------------
    protected User(String id) {
        super(id);
    }

    public User(String email, String password, Role role) {
        super(generateUUIDv4());

        this.email = isValidEmail(email, "email");
        this.password = password;
        this.role = role;
    }
}
