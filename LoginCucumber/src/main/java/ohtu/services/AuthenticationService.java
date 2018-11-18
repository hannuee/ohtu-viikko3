package ohtu.services;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

@Component
public class AuthenticationService {

    private UserDao userDao;
    
    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username
        int aValue = Character.getNumericValue('a');
        int zValue = Character.getNumericValue('z');
        
        if(username.length() < 3) {
            return true;
        }
        
        int i = 0;
        while(i < username.length()) {  // Check that all characters are on the range a-z.
            int iValue = Character.getNumericValue(username.charAt(i));
            boolean rangeOK = aValue <= iValue && iValue <= zValue;
            boolean isLowerCase = Character.isLowerCase(username.charAt(i));
            if(!rangeOK || !isLowerCase) {
                return true;
            }
            ++i;
        }
        
        if(userDao.findByName(username) != null) {
            return true;
        }
        
        
        // validity check of password
        if(password.length() < 8) {
            return true;
        }
        
        boolean numberOrSpecialExist = false;
        i = 0;
        while(i < password.length()) { 
            if(Character.isDigit(password.charAt(i)) || !Character.isAlphabetic(password.charAt(i))) {
                numberOrSpecialExist = true;
            }
            ++i;
        }
        
        if(!numberOrSpecialExist) {
            return true;
        }
        
        
        // Everything is ok.
        return false;
    }
}
