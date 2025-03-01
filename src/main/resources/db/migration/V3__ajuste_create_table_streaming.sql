CREATE TABLE IF NOT EXISTS streaming (
      id serial PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      );