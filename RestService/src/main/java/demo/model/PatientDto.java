package demo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class PatientDto {
  int id;
  
  String name;
  String details;
  
  int doctor;
  List<Integer> illnesses = new ArrayList<>();
}