package com.team1.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.team1.entity.User;
@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return User.class.equals(arg0);
	}

	public void validate(Object target, Errors erros) {
		User user = (User) target;
		if (user.getUsername() == null || user.getUsername().length() == 0 || user.getUsername().length() <= 4
				|| user.getUsername().length() >= 45) {
			erros.rejectValue("username", "Username.field.required");
		}
		if (user.getPassword() == null || user.getPassword().length() == 0 || user.getPassword().length() <= 4
				|| user.getPassword().length() >= 200) {
			erros.rejectValue("password", "Password.field.required");
		}
	}
}
