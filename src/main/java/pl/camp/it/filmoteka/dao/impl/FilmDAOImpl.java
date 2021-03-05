package pl.camp.it.filmoteka.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.camp.it.filmoteka.dao.IFilmDAO;
import pl.camp.it.filmoteka.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class FilmDAOImpl implements IFilmDAO {

    @Autowired
    Connection connection;

    @Override
    public Film getFilmByTitle(String title) {
        try {
            String sql = "SELECT * FROM tfilm WHERE title=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return this.mapResultSetToFilm(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFilm(Film film) {
        try {
            String sql = "UPDATE tfilm SET title=?, director=?, productionYear=?, length=?, category=? WHERE id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDirector());
            preparedStatement.setInt(3, film.getProductionYear());
            preparedStatement.setString(4, film.getLength());
            preparedStatement.setString(5, film.getCategory().toString());
            preparedStatement.setInt(6, film.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void persistFilm(Film film) {
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
    public Film getFilmById(int id) {
        try {
            String SQL = "SELECT * FROM tfilm WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return this.mapResultSetToFilm(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tfilm";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                films.add(this.mapResultSetToFilm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
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
