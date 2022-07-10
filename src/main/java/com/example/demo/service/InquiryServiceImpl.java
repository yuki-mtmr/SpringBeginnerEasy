package com.example.demo.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inquiry;
import com.example.demo.dao.InquiryDao;

@RequiredArgsConstructor
@Service
public class InquiryServiceImpl implements InquiryService{

	private final InquiryDao dao;

	
	@Override
	public void save(Inquiry inquiry) {
		dao.insertInquiry(inquiry);;
	}

	@Override
	public void update(Inquiry inquiry) {
		if(dao.updateInquiry(inquiry) == 0) {
			throw new InquiryNotFoundException("can't find the same ID");
		}
	}
	
	@Override
	public List<Inquiry> getAll() {
		return dao.getAll();
	}
}
