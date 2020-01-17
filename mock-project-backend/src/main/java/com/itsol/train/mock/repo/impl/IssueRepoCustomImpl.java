package com.itsol.train.mock.repo.impl;

import com.itsol.train.mock.repo.IssueRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

public class IssueRepoCustomImpl implements IssueRepoCustom {
    @Autowired
    EntityManagerFactory entityManagerFactory;

}
