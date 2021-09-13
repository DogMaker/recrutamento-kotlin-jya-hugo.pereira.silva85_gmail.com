package repository.entities

import domain.entities.Commit
import domain.entities.Pusher

data class Events(
        val action: String?,
        val created_at:  String?,
        val pusher: String,
        val commiterName: String
)
/*
CREATE TABLE books (
id              SERIAL PRIMARY KEY,
title           VARCHAR(100) NOT NULL,
primary_author  VARCHAR(100) NULL
);
*/