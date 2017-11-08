package org.shiksha.webapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    public List<User2> getAllUsers()
    {
        List<User2> userList = null;
        try
        {
            File file = new File("Users.json");
            if(!file.exists())
            {
                User2 us = new User2(1, "Harsha", "SDE");
                userList = new ArrayList<User2>();
                userList.add(us);
                saveUserList(userList);
            }
            else
            {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new FileReader(file));//"Users.json"));
                TypeToken<List<User2>> token = new TypeToken<List<User2>>() {};
                userList = gson.fromJson(reader, token.getType());
                reader.close();
            }
        }
        catch (IOException exp)
        {
            exp.printStackTrace();
        }
        return userList;
    }

    public User2 getUser(int id)
    {
        List<User2> users = getAllUsers();

        for(User2 user: users)
            if(user.getId() == id)
                return user;

        return null;
    }

    public int addUser(User2 user)
    {
        List<User2> users = getAllUsers();
        boolean userExists = false;
        for(User2 us: users)
        {
            if(us.getId() == user.getId())
            {
                userExists = true;
                break;
            }

        }
        if(!userExists)
        {
            users.add(user);
            saveUserList(users);
            return 0;
        }
        return 1;
    }

    public int updateUser(User2 user)
    {
        List<User2> users = getAllUsers();

        for(User2 us: users)
        {
            if(us.getId() == user.getId())
            {
                us.setName(user.getName());
                us.setProfession(user.getProfession());
                saveUserList(users);
                return 0;
            }
        }
        return 1;
    }

    public int deleteUser(int id)
    {
        List<User2> users = getAllUsers();

        for(User2 us: users)
        {
            if(us.getId() == id)
            {
                int ind = users.indexOf(us);
                users.remove(ind);
                saveUserList(users);
                return 0;
            }
        }
        return 1;
    }


    private void saveUserList(List<User2> userList)
    {
        Gson gson = new Gson();
        try
        {

            File file = new File("Users.json");
            FileOutputStream foutput = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(foutput, "UTF-8"));
            bufferedWriter.write(gson.toJson(userList));
            bufferedWriter.flush();
            bufferedWriter.close();

        }
        catch (FileNotFoundException exp)
        {
            exp.printStackTrace();
        }
        catch (IOException exp)
        {
            exp.printStackTrace();
        }
    }

}
