DROP TABLE IF EXISTS series;
CREATE TABLE series (
    id VARCHAR(36) NOT NULL,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    release_date DATE,
    created_date DATETIME NOT NULL,
    last_modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS episodes;
CREATE TABLE episodes (
    id VARCHAR(36) NOT NULL,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    episode_number INT,
    video_url VARCHAR(1000),
    series_id VARCHAR(36),
    release_date DATE,
    created_date DATETIME NOT NULL,
    last_modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;