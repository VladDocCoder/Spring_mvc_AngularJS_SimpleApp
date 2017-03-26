package web.demo.rest.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private long id;

    private String username;

    private String location;

    private String email;

    public User() {
        id = 0;
    }

    public User(long id, String username, String location, String email) {
        this.id = id;
        this.username = username;
        this.location = location;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
