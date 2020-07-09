package com.sms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sms.dtos.NewSubjectData;
import com.sms.models.Subject;
import com.sms.repositories.SubjectsRepository;

@Service
public class SubjectsService {
	private SubjectsRepository subjectsRepository;
	
	public SubjectsService(SubjectsRepository subjectsRepository) {
		this.subjectsRepository = subjectsRepository;
	}

	public void add(NewSubjectData data) {
		Subject subject = new Subject();
		subject.setCode(data.getCode());
		subject.setTitle(data.getTitle());
		
		subjectsRepository.save(subject);
	}

	public List<NewSubjectData> getAll() {
		return subjectsRepository.findAll()
		.stream().map(sub -> extractDTO(sub))
		.collect(Collectors.toList());
	}
	
	private NewSubjectData extractDTO(Subject sub) {
		NewSubjectData subject = new NewSubjectData();
		subject.setCode(sub.getCode());
		subject.setTitle(sub.getTitle());
		
		return subject;
	}
}
