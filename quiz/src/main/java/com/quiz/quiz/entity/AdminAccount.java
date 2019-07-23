package com.quiz.quiz.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin_account")
//@DiscriminatorValue(value = "admin_account")
public class AdminAccount extends Account{

}
