package ru.db.models;

import lombok.Data;


public interface EventInfo {
     String getName();
     String getCity();
     String getType();
     int getCount();
}
