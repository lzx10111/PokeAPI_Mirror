package com.example.PokemonAPI.model.dto;

import com.example.PokemonAPI.annotation.DateHtml;
import com.example.PokemonAPI.model.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotBlank(message = "{name.required}")
    @Pattern(regexp = "[a-zA-Z]{2,20}", message = "{name.pattern}")
    @Size(min = 3, max = 30, message = "{name.size}")
    @Column
    private String name;

    @NotBlank(message = "{username.required}")
    @Pattern(regexp = "\\w{3,20}", message = "{username.pattern}")
    @Size(min = 3, max = 30, message = "{username.size}")
    @Column
    private String username;

    @Column
    private String password;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.pattern}")
    @Column
    private String email;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "date_created")
    private Date dateCreated;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column
    private List<Role> roles;

    @Transient
    @NotBlank(message = "{passwordInput.required}")
    @Pattern(regexp = "\\w{3,30}", message = "{passwordInput.pattern}")
    @Size(min = 3, message = "{passwordInput.size}")
    private String passwordInput;

    @Transient
    @NotBlank(message = "{dateBirthInput.required}")
    @DateHtml(message = "{dateBirthInput.pattern}")
    private String dateBirthInput;

    public User(Integer id, String name, String username, String password, String email, Date dateBirth, Date dateCreated, List<Role> roles, String passwordInput, String dateBirthInput) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateBirth = dateBirth;
        this.dateCreated = dateCreated;
        this.roles = roles;
        this.passwordInput = passwordInput;
        this.dateBirthInput = dateBirthInput;
    }

    public User(Integer id, String name, String username, String password, String email, Date dateBirth, Date dateCreated, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateBirth = dateBirth;
        this.dateCreated = dateCreated;
        this.roles = roles;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String getDateBirthInput() {
        return dateBirthInput;
    }

    public void setDateBirthInput(String dateBirthInput) {
        this.dateBirthInput = Objects.requireNonNullElse(dateBirthInput, "");
    }
}
