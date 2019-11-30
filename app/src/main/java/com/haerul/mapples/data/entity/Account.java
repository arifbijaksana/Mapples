package com.haerul.mapples.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
    @PrimaryKey @NonNull
    public String id;
    public String account_name;
}
