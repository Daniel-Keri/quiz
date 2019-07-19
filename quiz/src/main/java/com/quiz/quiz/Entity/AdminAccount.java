package com.quiz.quiz.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin_account")
public class AdminAccount extends Account{

}
