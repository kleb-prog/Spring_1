package Lesson_7.services;

import Lesson_7.entities.Role;
import Lesson_7.entities.SystemUser;
import Lesson_7.entities.User;
import Lesson_7.repositories.RoleRepository;
import Lesson_7.repositories.UserRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private Channel channel;
	private final String QUEUE_NAME = "test";

	public UserServiceImpl() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		try {
			Connection connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		} catch (TimeoutException | IOException e) {
			e.printStackTrace();
		}

	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userRepository.findOneByUserName(userName);
	}


	@Override
	@Transactional
	public void save(SystemUser systemUser) {
		User user = new User();
		user.setUserName(systemUser.getUserName());
		user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
		user.setFirstName(systemUser.getFirstName());
		user.setLastName(systemUser.getLastName());
		user.setEmail(systemUser.getEmail());

		user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_EMPLOYEE")));

		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findOneByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		String msg = userName + " have entered";
		try {
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
