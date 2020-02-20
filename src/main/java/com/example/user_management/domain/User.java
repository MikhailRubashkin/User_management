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
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
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

    public Set<Role> getRoles () {
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

    @Override
    public boolean equals ( Object o ){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;

        User user = (User) o;

        if (active != user.active) return false;
        if (id != null ? !id.equals (user.id) : user.id != null) return false;
        if (username != null ? !username.equals (user.username) : user.username != null) return false;
        if (password != null ? !password.equals (user.password) : user.password != null) return false;
        if (first_Name != null ? !first_Name.equals (user.first_Name) : user.first_Name != null) return false;
        if (last_Name != null ? !last_Name.equals (user.last_Name) : user.last_Name != null) return false;
        if (localDate != null ? !localDate.equals (user.localDate) : user.localDate != null) return false;
        return roles != null ? roles.equals (user.roles) : user.roles == null;
    }

    @Override
    public int hashCode (){
        int result = id != null ? id.hashCode ( ) : 0;
        result = 31 * result+(username != null ? username.hashCode ( ) : 0);
        result = 31 * result+(password != null ? password.hashCode ( ) : 0);
        result = 31 * result+(first_Name != null ? first_Name.hashCode ( ) : 0);
        result = 31 * result+(last_Name != null ? last_Name.hashCode ( ) : 0);
        result = 31 * result+(active ? 1 : 0);
        result = 31 * result+(localDate != null ? localDate.hashCode ( ) : 0);
        result = 31 * result+(roles != null ? roles.hashCode ( ) : 0);
        return result;
    }

    @Override
    public String toString (){
        return "User{"+
               "id="+id+
               ", username='"+username+'\''+
               ", password='"+password+'\''+
               ", first_Name='"+first_Name+'\''+
               ", last_Name='"+last_Name+'\''+
               ", active="+active+
               ", localDate="+localDate+
               ", roles="+roles+
               '}';
    }
}
