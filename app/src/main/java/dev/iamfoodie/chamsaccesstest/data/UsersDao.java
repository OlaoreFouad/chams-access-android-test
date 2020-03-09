package dev.iamfoodie.chamsaccesstest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import dev.iamfoodie.chamsaccesstest.models.User;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    LiveData<List<User>> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

}
