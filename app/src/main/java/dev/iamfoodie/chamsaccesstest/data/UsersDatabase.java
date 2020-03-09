package dev.iamfoodie.chamsaccesstest.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dev.iamfoodie.chamsaccesstest.models.User;

@Database(
        entities = {User.class},
        exportSchema = false,
        version = 1
)
public abstract class UsersDatabase extends RoomDatabase {

    public abstract UsersDao getDao();
    private static UsersDatabase INSTANCE;

    public static UsersDatabase getInstance(Application application) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    application.getApplicationContext(), UsersDatabase.class, "users_db"
            ).fallbackToDestructiveMigration().build();
        }

        return INSTANCE;

    }

}
