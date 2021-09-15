CREATE TABLE illnesses (
  id              serial              PRIMARY KEY,
  name            varchar             NOT NULL UNIQUE,
  details         varchar
);

CREATE TABLE doctors (
  id              serial              PRIMARY KEY,
  name            varchar             NOT NULL UNIQUE,
  details         varchar
);

CREATE TABLE patients (
  id              serial              PRIMARY KEY,
  name            varchar             NOT NULL UNIQUE,
  details         varchar,
  doctor          int       CONSTRAINT patients_doctor REFERENCES doctors( id ) ON DELETE SET NULL
);

-- @ManyToMany  patients and illnesses
CREATE TABLE patients_illnesses (
  patient         int       NOT NULL CONSTRAINT patients_illnesses_p REFERENCES patients( id ) ON DELETE CASCADE,
  illness         int       NOT NULL CONSTRAINT patients_illnesses_i REFERENCES illnesses( id ) ON DELETE CASCADE,
  PRIMARY KEY ( illness, patient )
);

INSERT INTO illnesses ( name, details ) VALUES ( 'Covid-19', 'Symptoms of COVID-19 are variable, but often include fever, cough, headache, fatigue, breathing difficulties, and loss of smell and taste.' );
INSERT INTO illnesses ( name, details ) VALUES ( 'Common cold', 'Signs and symptoms may appear less than two days after exposure to the virus.' );
