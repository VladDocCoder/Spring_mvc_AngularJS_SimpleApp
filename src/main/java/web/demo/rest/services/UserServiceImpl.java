package web.demo.rest.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import web.demo.rest.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService{


	private final AtomicLong generic = new AtomicLong();
    private  List<User> users = new ArrayList<>();
	
	public UserServiceImpl(){

        File file = new File("WorkersService.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectInputStream inputPerson = new ObjectInputStream(new FileInputStream(file));
            users = (ArrayList<User>) inputPerson.readObject();
            inputPerson.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
	public List<User> getAllUsers() {
		return users;
	}
    @Override
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
         }
		return null;
	}
    @Override
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}

    @Override
	public void saveUser(User user) {
		user.setId(generic.incrementAndGet());
		users.add(user);

        try {
            ObjectOutputStream outputPerson = new ObjectOutputStream(new FileOutputStream("WorkersService.txt"));
            outputPerson.writeObject(users);
            outputPerson.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
    @Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
        try {
            ObjectOutputStream outputPerson = new ObjectOutputStream(new FileOutputStream("WorkersService.txt"));
            outputPerson.writeObject(users);
            outputPerson.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    @Override
	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
                try {
                    ObjectOutputStream outputPerson = new ObjectOutputStream(new FileOutputStream("WorkersService.txt"));
                    outputPerson.writeObject(users);
                    outputPerson.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
		    }
		}
    }
    @Override
	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
    }

}
