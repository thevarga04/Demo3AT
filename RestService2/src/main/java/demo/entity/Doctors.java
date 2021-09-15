package demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table( name = "doctors" )
public class Doctors {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private int id;
  
  @NotNull
  private String name;
  private String details;
}