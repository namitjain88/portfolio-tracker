package com.springboot.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.document.User;
import com.springboot.mongodb.utility.NextSequenceService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NextSequenceService nextSequenceService;

	private static final String seqName = "userSequence";

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findById(Integer.valueOf(id)).get();
	}

	public User createUser(User newUser) {
		newUser.setId(nextSequenceService.getNextSequence(UserService.seqName).intValue());
		return userRepository.insert(newUser);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	/*
	 * public User getUserByName(String firstName) { return
	 * userRepository.getUserByFirstName(firstName); }
	 */

}
