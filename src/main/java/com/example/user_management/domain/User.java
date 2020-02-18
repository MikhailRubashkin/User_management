package com.example.user_management.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String first_Name;
    private String last_Name;
    private boolean active;
    @Temporal(TemporalType.DATE)
    private Date localDate;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired (){
        return true;
    }

    @Override
    public boolean isAccountNonLocked (){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired (){
        return true;
    }

    @Override
    public boolean isEnabled (){
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities (){
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirst_Name (){
        return first_Name;
    }

    public void setFirst_Name ( String first_Name ){
        this.first_Name = first_Name;
    }

    public String getLast_Name (){
        return last_Name;
    }

    public void setLast_Name ( String last_Name ){
        this.last_Name = last_Name;
    }

    public Date getLocalDate (){
        return localDate;
    }

    public void setLocalDate ( Date localDate ){
        this.localDate = localDate;
    }
}
