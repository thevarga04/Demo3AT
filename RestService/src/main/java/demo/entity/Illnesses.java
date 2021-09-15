package demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table( name = "illnesses" )
public class Illnesses {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private int id;
  
  @NotNull
  private String name;
  private String details;
}