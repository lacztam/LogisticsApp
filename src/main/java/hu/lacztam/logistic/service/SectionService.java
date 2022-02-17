package hu.lacztam.logistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired SectionRepository sectionRepository;
	
}
