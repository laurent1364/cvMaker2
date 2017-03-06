#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id_u              int (11) Auto_increment  NOT NULL ,
        username          Varchar (250) NOT NULL ,
        passwordEncrypted Varchar (250) NOT NULL ,
        id_ui             Int NOT NULL ,
        PRIMARY KEY (id_u )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_information
#------------------------------------------------------------

CREATE TABLE user_information(
        id_ui               int (11) Auto_increment  NOT NULL ,
        first_name          Varchar (250) NOT NULL ,
        last_name           Varchar (250) ,
        phone_number        Varchar (25) ,
        email               Varchar (250) ,
        birth_date          Date ,
        is_male             Bool ,
        has_diving_licence  Bool ,
        profile_picture_url Varchar (250) ,
        id_u                Int NOT NULL ,
        id_a                Int ,
        PRIMARY KEY (id_ui )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: address
#------------------------------------------------------------

CREATE TABLE address(
        id_a               int (11) Auto_increment  NOT NULL ,
        street_address     Varchar (250) ,
        additional_address Varchar (250) ,
        city               Varchar (250) ,
        state              Varchar (250) ,
        zip_code           Int ,
        country            Varchar (250) ,
        PRIMARY KEY (id_a )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: referee
#------------------------------------------------------------

CREATE TABLE referee(
        id_r         int (11) Auto_increment  NOT NULL ,
        first_name   Varchar (250) ,
        last_name    Varchar (250) ,
        is_male      Bool ,
        function     Varchar (250) ,
        email        Varchar (250) ,
        phone_number Varchar (250) ,
        id_o         Int ,
        id_u         Int NOT NULL ,
        PRIMARY KEY (id_r )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: organisation
#------------------------------------------------------------

CREATE TABLE organisation(
        id_o                  int (11) Auto_increment  NOT NULL ,
        name                  Varchar (250) ,
        website               Varchar (250) ,
        organisation_logo_url Varchar (250) ,
        id_a                  Int ,
        id_e                  Int NOT NULL ,
        PRIMARY KEY (id_o )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: hobby
#------------------------------------------------------------

CREATE TABLE hobby(
        id_h int (11) Auto_increment  NOT NULL ,
        name Varchar (250) ,
        id_l Int ,
        PRIMARY KEY (id_h )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: logo
#------------------------------------------------------------

CREATE TABLE logo(
        id_l  int (11) Auto_increment  NOT NULL ,
        name  Varchar (250) ,
        class Varchar (250) ,
        PRIMARY KEY (id_l )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: experience
#------------------------------------------------------------

CREATE TABLE experience(
        id_e        int (11) Auto_increment  NOT NULL ,
        title       Varchar (250) ,
        location    Varchar (250) ,
        description Text ,
        honnor      Varchar (5000) ,
        id_l        Int ,
        id_et       Int ,
        id_o        Int ,
        id_u        Int NOT NULL ,
        PRIMARY KEY (id_e )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: experience_type
#------------------------------------------------------------

CREATE TABLE experience_type(
        id_et int (11) Auto_increment  NOT NULL ,
        name  Varchar (250) ,
        PRIMARY KEY (id_et )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: achievement
#------------------------------------------------------------

CREATE TABLE achievement(
        id_ach      int (11) Auto_increment  NOT NULL ,
        description Text NOT NULL ,
        id_e        Int NOT NULL ,
        PRIMARY KEY (id_ach )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: skill
#------------------------------------------------------------

CREATE TABLE skill(
        id_s        int (11) Auto_increment  NOT NULL ,
        title       Varchar (250) NOT NULL ,
        description Varchar (2000) ,
        id_l        Int ,
        id_d        Int NOT NULL ,
        PRIMARY KEY (id_s )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: domain
#------------------------------------------------------------

CREATE TABLE domain(
        id_d  int (11) Auto_increment  NOT NULL ,
        title Varchar (2000) ,
        id_l  Int ,
        PRIMARY KEY (id_d )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resume
#------------------------------------------------------------

CREATE TABLE resume(
        id_res            int (11) Auto_increment  NOT NULL ,
        introduction      Text ,
        job_seek          Text ,
        title             Varchar (250) NOT NULL ,
        hobby_description Text ,
        id_u              Int NOT NULL ,
        id_t              Int NOT NULL ,
        PRIMARY KEY (id_res )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: template
#------------------------------------------------------------

CREATE TABLE template(
        id_t int (11) Auto_increment  NOT NULL ,
        name Varchar (250) ,
        PRIMARY KEY (id_t )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: social_media
#------------------------------------------------------------

CREATE TABLE social_media(
        id_sm int (11) Auto_increment  NOT NULL ,
        name  Varchar (250) NOT NULL ,
        id_l  Int ,
        PRIMARY KEY (id_sm )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: social_network
#------------------------------------------------------------

CREATE TABLE social_network(
        id_sn        int (11) Auto_increment  NOT NULL ,
        link_address Varchar (250) ,
        id_ui        Int NOT NULL ,
        id_sm        Int NOT NULL ,
        PRIMARY KEY (id_sn )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Role
#------------------------------------------------------------

CREATE TABLE Role(
        id_ro int (11) Auto_increment  NOT NULL ,
        role  Varchar (250) NOT NULL ,
        PRIMARY KEY (id_ro )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_hobbies
#------------------------------------------------------------

CREATE TABLE user_hobbies(
        id_u Int NOT NULL ,
        id_h Int NOT NULL ,
        PRIMARY KEY (id_u ,id_h )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_skills
#------------------------------------------------------------

CREATE TABLE user_skills(
        mark Int ,
        id_u Int NOT NULL ,
        id_s Int NOT NULL ,
        PRIMARY KEY (id_u ,id_s )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resumes_hobbies
#------------------------------------------------------------

CREATE TABLE resumes_hobbies(
        id_res Int NOT NULL ,
        id_h   Int NOT NULL ,
        PRIMARY KEY (id_res ,id_h )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resumes_experiences
#------------------------------------------------------------

CREATE TABLE resumes_experiences(
        id_res Int NOT NULL ,
        id_e   Int NOT NULL ,
        PRIMARY KEY (id_res ,id_e )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resumes_skills
#------------------------------------------------------------

CREATE TABLE resumes_skills(
        id_res Int NOT NULL ,
        id_s   Int NOT NULL ,
        PRIMARY KEY (id_res ,id_s )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resumes_referees
#------------------------------------------------------------

CREATE TABLE resumes_referees(
        id_res Int NOT NULL ,
        id_r   Int NOT NULL ,
        PRIMARY KEY (id_res ,id_r )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: resumes_social_networks
#------------------------------------------------------------

CREATE TABLE resumes_social_networks(
        id_res Int NOT NULL ,
        id_sn  Int NOT NULL ,
        PRIMARY KEY (id_res ,id_sn )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_roles
#------------------------------------------------------------

CREATE TABLE user_roles(
        id_u  Int NOT NULL ,
        id_ro Int NOT NULL ,
        PRIMARY KEY (id_u ,id_ro )
)ENGINE=InnoDB;

ALTER TABLE user ADD CONSTRAINT FK_user_id_ui FOREIGN KEY (id_ui) REFERENCES user_information(id_ui);
ALTER TABLE user_information ADD CONSTRAINT FK_user_information_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE user_information ADD CONSTRAINT FK_user_information_id_a FOREIGN KEY (id_a) REFERENCES address(id_a);
ALTER TABLE referee ADD CONSTRAINT FK_referee_id_o FOREIGN KEY (id_o) REFERENCES organisation(id_o);
ALTER TABLE referee ADD CONSTRAINT FK_referee_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE organisation ADD CONSTRAINT FK_organisation_id_a FOREIGN KEY (id_a) REFERENCES address(id_a);
ALTER TABLE organisation ADD CONSTRAINT FK_organisation_id_e FOREIGN KEY (id_e) REFERENCES experience(id_e);
ALTER TABLE hobby ADD CONSTRAINT FK_hobby_id_l FOREIGN KEY (id_l) REFERENCES logo(id_l);
ALTER TABLE experience ADD CONSTRAINT FK_experience_id_l FOREIGN KEY (id_l) REFERENCES logo(id_l);
ALTER TABLE experience ADD CONSTRAINT FK_experience_id_et FOREIGN KEY (id_et) REFERENCES experience_type(id_et);
ALTER TABLE experience ADD CONSTRAINT FK_experience_id_o FOREIGN KEY (id_o) REFERENCES organisation(id_o);
ALTER TABLE experience ADD CONSTRAINT FK_experience_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE achievement ADD CONSTRAINT FK_achievement_id_e FOREIGN KEY (id_e) REFERENCES experience(id_e);
ALTER TABLE skill ADD CONSTRAINT FK_skill_id_l FOREIGN KEY (id_l) REFERENCES logo(id_l);
ALTER TABLE skill ADD CONSTRAINT FK_skill_id_d FOREIGN KEY (id_d) REFERENCES domain(id_d);
ALTER TABLE domain ADD CONSTRAINT FK_domain_id_l FOREIGN KEY (id_l) REFERENCES logo(id_l);
ALTER TABLE resume ADD CONSTRAINT FK_resume_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE resume ADD CONSTRAINT FK_resume_id_t FOREIGN KEY (id_t) REFERENCES template(id_t);
ALTER TABLE social_media ADD CONSTRAINT FK_social_media_id_l FOREIGN KEY (id_l) REFERENCES logo(id_l);
ALTER TABLE social_network ADD CONSTRAINT FK_social_network_id_ui FOREIGN KEY (id_ui) REFERENCES user_information(id_ui);
ALTER TABLE social_network ADD CONSTRAINT FK_social_network_id_sm FOREIGN KEY (id_sm) REFERENCES social_media(id_sm);
ALTER TABLE user_hobbies ADD CONSTRAINT FK_user_hobbies_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE user_hobbies ADD CONSTRAINT FK_user_hobbies_id_h FOREIGN KEY (id_h) REFERENCES hobby(id_h);
ALTER TABLE user_skills ADD CONSTRAINT FK_user_skills_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE user_skills ADD CONSTRAINT FK_user_skills_id_s FOREIGN KEY (id_s) REFERENCES skill(id_s);
ALTER TABLE resumes_hobbies ADD CONSTRAINT FK_resumes_hobbies_id_res FOREIGN KEY (id_res) REFERENCES resume(id_res);
ALTER TABLE resumes_hobbies ADD CONSTRAINT FK_resumes_hobbies_id_h FOREIGN KEY (id_h) REFERENCES hobby(id_h);
ALTER TABLE resumes_experiences ADD CONSTRAINT FK_resumes_experiences_id_res FOREIGN KEY (id_res) REFERENCES resume(id_res);
ALTER TABLE resumes_experiences ADD CONSTRAINT FK_resumes_experiences_id_e FOREIGN KEY (id_e) REFERENCES experience(id_e);
ALTER TABLE resumes_skills ADD CONSTRAINT FK_resumes_skills_id_res FOREIGN KEY (id_res) REFERENCES resume(id_res);
ALTER TABLE resumes_skills ADD CONSTRAINT FK_resumes_skills_id_s FOREIGN KEY (id_s) REFERENCES skill(id_s);
ALTER TABLE resumes_referees ADD CONSTRAINT FK_resumes_referees_id_res FOREIGN KEY (id_res) REFERENCES resume(id_res);
ALTER TABLE resumes_referees ADD CONSTRAINT FK_resumes_referees_id_r FOREIGN KEY (id_r) REFERENCES referee(id_r);
ALTER TABLE resumes_social_networks ADD CONSTRAINT FK_resumes_social_networks_id_res FOREIGN KEY (id_res) REFERENCES resume(id_res);
ALTER TABLE resumes_social_networks ADD CONSTRAINT FK_resumes_social_networks_id_sn FOREIGN KEY (id_sn) REFERENCES social_network(id_sn);
ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_id_u FOREIGN KEY (id_u) REFERENCES user(id_u);
ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_id_ro FOREIGN KEY (id_ro) REFERENCES Role(id_ro);
