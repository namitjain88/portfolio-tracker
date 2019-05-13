package com.springboot.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.document.User;
import com.springboot.mongodb.repository.UserService;
import com.springboot.mongodb.utility.NextSequenceService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private NextSequenceService nextSequenceService;

	private static final String seqName = "userSequence";

	/*
	 * @GetMapping("") public List<User> getAllUsers() { return
	 * userService.getAllUsers(); }
	 */

	@GetMapping("")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<List<User>>(users, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}

	/*
	 * @GetMapping("") public User getUserByName(@RequestParam(value =
	 * "firstName") String firstName) { return
	 * userService.getUserByName(firstName); }
	 */

	@PostMapping("")
	public User createUser(@RequestBody User newUser) {
		newUser.setId(nextSequenceService.getNextSequence(UserResource.seqName).intValue());
		return userService.createUser(newUser);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		return userService.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

}
