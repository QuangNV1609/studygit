package com.quangnv.uet.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.extend.CustomerDto;
import com.quangnv.uet.dto.extend.ImageDto;
import com.quangnv.uet.entities.orm.CustomerEntity;
import com.quangnv.uet.entities.orm.ImageEntity;
import com.quangnv.uet.entities.orm.UserEntity;
import com.quangnv.uet.repository.CustomerRepository;
import com.quangnv.uet.repository.ImageRepository;
import com.quangnv.uet.repository.UserRepository;
import com.quangnv.uet.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDto getCustomerByUserId(String userId) {
		log.info("Sql get customer");
		Optional<CustomerEntity> optional = Optional
				.ofNullable(customerRepository.findOneByUserId(userId).orElse(null));

		if (optional.isPresent()) {
			CustomerEntity customerEntity = optional.get();
			CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
			ImageDto imageDto = customerDto.getImage();
			imageDto.setDownloadUri("http://localhost:8081/image/download/" + imageDto.getImageId());
			return customerDto;
		} else {
			return null;
		}
	}

	@Override
	public CustomerDto saveCustomerByUserId(String userId, CustomerDto customerDto) {
		Optional<UserEntity> optional = Optional.ofNullable(userRepository.findById(userId).orElse(null));
		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
			customerEntity.setUser(userEntity);
			customerEntity = customerRepository.save(customerEntity);
			return modelMapper.map(customerEntity, CustomerDto.class);
		} else {
			throw new UsernameNotFoundException(userId);
		}
	}

	@Override
	public ImageDto getCustomerImageByCustomerId(String customerId) {
		ImageEntity imageEntity = imageRepository.findOneByCustomer(customerId);
		ImageDto imageDto = modelMapper.map(imageEntity, ImageDto.class);
		imageDto.setDownloadUri("http://localhost:8081/image/download/" + imageDto.getImageId());
		return imageDto;
	}

}
