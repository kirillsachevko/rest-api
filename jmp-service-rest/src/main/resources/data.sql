DROP users;
DROP subscriptions;

CREATE TABLE users(
    id INT NOT NULL,
    name VARCHAR(32),
    surname VARCHAR(64),
    birthday TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE subscriptions(
    id INT NOT NULL,
    user_id INT,
    start_date TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);