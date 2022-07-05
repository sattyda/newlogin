package ncs.demo.services;

import ncs.demo.entities.Product;
import ncs.demo.entities.Sessions;
import ncs.demo.entities.User;
import ncs.demo.repos.KeysRepo;
import ncs.demo.repos.ProductRepo;
import ncs.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    KeysRepo keysRepo;

    @Autowired
    ProductRepo productRepo;

    public boolean saveUser(User user) {

        List<User> users =  userRepo.findAllByUsername(user.getUsername());

        if( users.isEmpty() ){
            userRepo.save(user);
            return true;
        }  else {
            return false;
        }
    }

    public boolean loginUser(User user) {
        List<User> users =  userRepo.findAllByUsername(user.getUsername());
        if( users.isEmpty() ){
            return false;
        }  else {
            if( users.get(0).getPassword().equals(user.getPassword()) ){
                return true;
            }
        }
        return false;
    }

    public void saveKey(Sessions keys) {
        keysRepo.save(keys);
    }

    public boolean logout(Sessions sessions) {
        List<Sessions> sessionsList =  keysRepo.findAllByUsername(sessions.getUsername());
        if( sessionsList.isEmpty() ){
            return false;
        }  else {
            if( sessionsList.get(0).getSessionkey().equals(sessions.getSessionkey()) ){
                keysRepo.delete(sessionsList.get(0));
                return true;
            }
        }
        return false;

    }

    public boolean validateUser(Sessions sessions) {
        List<Sessions> sessionsList =  keysRepo.findAllByUsername(sessions.getUsername());
        if( sessionsList.isEmpty() ){
            return false;
        }  else {
            if( sessionsList.get(0).getSessionkey().equals(sessions.getSessionkey()) ){
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductList() {
        return productRepo.findAll();
    }
}