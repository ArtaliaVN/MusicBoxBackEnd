package Artalia.com.example.MusicBox.Service.Role;

import Artalia.com.example.MusicBox.Service.ServiceInterface.EntityHandler;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class RoleEntity extends EntityHandler{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ApplicationRole roleName;
}
