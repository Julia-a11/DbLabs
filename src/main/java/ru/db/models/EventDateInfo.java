package ru.db.models;

import lombok.Data;

import java.sql.Date;

public interface EventDateInfo {
     Date getDate();
     int getCount();
}
