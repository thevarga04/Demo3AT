package demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table( name = "patients" )
public class Patients {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private int id;
  
  @NotNull
  private String name;
  private String details;
  
  @ManyToOne( fetch = FetchType.EAGER, optional = false )
  @JoinColumn( name = "doctor" )
  @OrderColumn( name = "id" )
  private Doctors doctors;
  
  @ManyToMany( fetch = FetchType.LAZY )
  @JoinTable( name = "patients_illnesses",
    joinColumns = { @JoinColumn( name = "patient") },
    inverseJoinColumns = { @JoinColumn( name = "illness") })
  private Set<Illnesses> illnesses = new HashSet<>();
}