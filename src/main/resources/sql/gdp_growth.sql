CREATE TABLE gdp_growth (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT NOT NULL,
    year INT NOT NULL,
    growth_percentage DECIMAL(10,6),
    FOREIGN KEY (country_id) REFERENCES countries(id),
    UNIQUE (country_id, year)
);