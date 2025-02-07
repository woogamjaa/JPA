package com.jpa.model.복합키entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentSubjectKeyMapping implements Serializable {

    private StudentEntity3 student;
    private SubjectEntity3 subject;
}
