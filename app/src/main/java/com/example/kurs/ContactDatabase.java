package com.example.kurs;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


// Обновляем версию схемы на 3
@Database(entities = {ModelContact.class}, version = 3, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static volatile ContactDatabase INSTANCE;

    public static ContactDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ContactDatabase.class, "contact_database")
                            .fallbackToDestructiveMigration() // Очищает базу данных при ошибках миграции
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}



