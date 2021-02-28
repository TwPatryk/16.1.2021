package pl.camp.it.filmoteka.dataBase.impl;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.filmoteka.dataBase.IFilmRepository;
import pl.camp.it.filmoteka.model.Film;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFilmRepositoryImpl implements IFilmRepository {

    @Autowired
    Connection connection;

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tfilm";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                films.add(this.mapResultSetToFilm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public List<Film> getFilmsByCategory(Film.Category category) {
        List<Film> films = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tfilm WHERE category=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, category.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                films.add(this.mapResultSetToFilm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }


    @Override
    public Film getFilmByTitle(String title) {
        try {
            String SQL = "SELECT * FROM tfilm WHERE title=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return this.mapResultSetToFilm(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addFilm(Film film) {
        try {
            String SQL = "INSERT INTO tfilm (title, director, productionYear, length, category) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDirector());
            preparedStatement.setInt(3, film.getProductionYear());
            preparedStatement.setString(4, film.getLength());
            preparedStatement.setString(5, film.getCategory().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFilm(Film film) {
        try {
            String SQL = "UPDATE tfilm SET director=?, productionYear=?, length=?, category=? WHERE title=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, film.getDirector());
            preparedStatement.setInt(2, film.getProductionYear());
            preparedStatement.setString(3, film.getLength());
            preparedStatement.setString(4, film.getCategory().toString());
            preparedStatement.setString(5, film.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Film mapResultSetToFilm(ResultSet resultSet) throws SQLException {

        Film film = new Film();
        film.setId(resultSet.getInt("id"));
        film.setTitle(resultSet.getString("title"));
        film.setDirector(resultSet.getString("director"));
        film.setProductionYear(resultSet.getInt("productionYear"));
        film.setLength(resultSet.getString("length"));
        film.setCategory(Film.Category.valueOf(resultSet.getString("category")));

        return film;
    }
}
