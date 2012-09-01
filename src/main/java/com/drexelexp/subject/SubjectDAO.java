package com.drexelexp.subject;

import java.util.List;

/**
 * DAO interface for the Subject.
 * @author
 *
 */
public interface SubjectDAO {
	public void insert(Subject instance);
	public Subject search(String subjectname);
	public List<Subject> listSubjects();
	public void delete(Subject instance);
}
