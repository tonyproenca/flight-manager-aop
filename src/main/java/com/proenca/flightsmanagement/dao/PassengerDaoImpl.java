package com.proenca.flightsmanagement.dao;

import com.proenca.flightsmanagement.domain.Passenger;
import com.proenca.flightsmanagement.exceptions.CountryDoesNotExistsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.util.Arrays.asList;

public class PassengerDaoImpl implements PassengerDao {
    private static Map<Integer, Passenger> passengerMap = new HashMap<>();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Passenger> rowMapper = (resultSet, rowNum) -> {
        Passenger passenger = new Passenger();
        passenger.setName(resultSet.getString("name"));
        passenger.setCountry(resultSet.getString("country"));
        return passenger;
    };

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    private Passenger getById(int id) {
        String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Passenger getPassenger(int id) {
        if (null != passengerMap.get(id)) {
            return passengerMap.get(id);
        }

        return getById(id);
    }

    @Override
    public void insertPassenger(Passenger passenger) {
        if (!asList(Locale.getISOCountries()).contains(passenger.getCountry())) {
            throw new CountryDoesNotExistsException(passenger.getCountry());
        }

        String sql = "INSERT INTO PASSENGER (NAME, COUNTRY) VALUES (?, ?)";
        jdbcTemplate.update(sql, passenger.getName(), passenger.getCountry());
    }

    public static Map<Integer, Passenger> getPassengerMap() {
        return passengerMap;
    }
}
