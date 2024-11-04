CREATE TABLE IF NOT EXISTS `user`(
	id INT AUTO_INCREMENT,
    `name` VARCHAR(75) NOT NULL,
    email VARCHAR(75) NOT NULL UNIQUE,
    `password` VARCHAR(75) NOT NULL,
	`role` TINYINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT user_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post(
	id INT AUTO_INCREMENT,
    title VARCHAR(75) NOT NULL,
    `description` VARCHAR(200),
    link TINYTEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_id INT NOT NULL,
    CONSTRAINT post_id PRIMARY KEY (id),
    CONSTRAINT user_post_id FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE IF NOT EXISTS `event`(
	id INT AUTO_INCREMENT,
    title VARCHAR(75) NOT NULL,
    `description` TINYTEXT,
    `date` TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_id INT NOT NULL,
    CONSTRAINT event_id PRIMARY KEY (id),
    CONSTRAINT user_event_id FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE IF NOT EXISTS enrollment_user_event(
	id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    `status` TINYINT NOT NULL DEFAULT 1,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT user_enrollment_user_event_id FOREIGN KEY (user_id) REFERENCES `user` (id),
    CONSTRAINT event_enrollment_user_event_id FOREIGN KEY (event_id) REFERENCES `event` (id)
);
