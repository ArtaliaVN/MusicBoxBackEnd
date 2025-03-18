package Artalia.com.example.MusicBox.Service.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleName(ApplicationRole roleName);
}
