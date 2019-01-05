package com.cts.iiht.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.iiht.dao.SubjectDao;
import com.cts.iiht.entity.Subject;

@Service
public class SubjectService {
	@Autowired
	SubjectDao subjectDao;
	
	public void addSubject(Subject subject) {
		subjectDao.addSubject(subject);
	}
	
	public void deleteSubject(long id) {
		subjectDao.deleteSubject(id);
	}

	public Subject searchSubject(long id) {
		return subjectDao.searchSubject(id);
	}
	public List<Subject> getAllSubjects() {
		return subjectDao.getAllSubjects();
	}

	public List<Subject> getAllSubjectsSortBySubTitle(){
		return subjectDao.getAllSubjectsSortBySubTitle();
	}
}
