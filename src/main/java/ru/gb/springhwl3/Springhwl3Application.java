package ru.gb.springhwl3;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.gb.springhwl3.entity.Book;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@SpringBootApplication
public class Springhwl3Application {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Springhwl3Application.class, args);

		/*
		DataSource dataSource = context.getBean(DataSource.class);

		try (Connection connection = dataSource.getConnection()){
			try (Statement statement = connection.createStatement()){
				statement.execute("CREATE TABLE books(id BIGINT, name VARCHAR(50))");
			}

			try (Statement statement = connection.createStatement()){
				statement.execute("INSERT INTO books (id, name) VALUES (1, 'Война и мир'), (2, 'Мастер и Маргарита'), " +
						"(3, 'Приключения Буратино'), (4, 'Красная шапочка'), (5, 'Семеро козлят')");
			}

			try (Statement statement = connection.createStatement()){
				ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

				while (resultSet.next()){
					System.out.print(resultSet.getInt("id"));
					System.out.println(" - " + resultSet.getString("name"));
				}
			}
		}

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

		jdbcTemplate.execute("CREATE TABLE books(id BIGINT, name VARCHAR(50))");
		jdbcTemplate.execute("INSERT INTO books (id, name) VALUES (1, 'Война и мир'), (2, 'Мастер и Маргарита'), " +
				"(3, 'Приключения Буратино'), (4, 'Красная шапочка'), (5, 'Семеро козлят')");
		List<Book> books = jdbcTemplate.query("SELECT * FROM books",
				(rs, rowNum) -> new Book(rs.getLong("id"),
						rs.getString("name")));
		System.out.println(books);*/


	}
}