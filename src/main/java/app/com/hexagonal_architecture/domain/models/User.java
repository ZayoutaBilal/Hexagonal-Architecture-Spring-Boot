package app.com.hexagonal_architecture.domain.models;


import app.com.hexagonal_architecture.domain.enums.Role;

public class User {

    public User() {}

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Boolean deleted;
    private UserDetails userDetails;

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
