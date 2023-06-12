package training.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
public enum Role {
    ADMIN(Set.of(Permission.TODO_READ, Permission.TODO_READ_ALL, Permission.TODO_CREATE, Permission.TODO_UPDATE, Permission.TODO_DELETE)),
    MEMBER(Set.of(Permission.TODO_READ, Permission.TODO_UPDATE)),
    ANALYST(Set.of(Permission.TODO_READ, Permission.TODO_READ_ALL, Permission.TODO_CREATE, Permission.TODO_UPDATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permission){
        this.permissions = permission;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedPermissions() {
       return getPermissions().stream().map(permission ->
               new SimpleGrantedAuthority("ROLE_" + permission.name())).collect(Collectors.toSet());
    }

}
