package main.java.com.excilys.projet.java.cdb.persistence.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.com.excilys.projet.java.cdb.mapper.ComputerMapper;
import main.java.com.excilys.projet.java.cdb.models.Computer;
import main.java.com.excilys.projet.java.cdb.models.Page;
import main.java.com.excilys.projet.java.cdb.persistence.Connect;

public class ComputerDAO extends Dao<Computer> {
    private static ComputerDAO computerDAO;
    private Connection connect = Connect.getInstance();

    /**
     * Private constructor of ComputerDAO.
     */
    private ComputerDAO() {
    }

    /**
     * Instance of the singleton ComputerDAO.
     * @return the instance of ComputerDAO
     */
    public static synchronized ComputerDAO getInstance() {
        if (computerDAO == null) {
            computerDAO = new ComputerDAO();
        }
        return computerDAO;
    }

    @Override
    public List<Computer> getAll() {
        List<Computer> computerList = new ArrayList<Computer>();

        try (PreparedStatement statement = connect.prepareStatement("SELECT computer.id, computer.name,"
        		+ "introduced, discontinued, company_id, company.name AS company_name FROM computer "
        		+ "LEFT JOIN company ON company_id = company.id ORDER BY computer.id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Computer computer = ComputerMapper.getComputer(resultSet);
                computerList.add(computer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computerList;
    }

    /**
     * Get all the computers on a given page.
     * @param page
     * @return list of the computers
     */
    public List<Computer> getAllByPage(Page page) {
        List<Computer> computerList = new ArrayList<Computer>();

        if (page.getCurrentPage() > 0) {

            try (PreparedStatement statement = connect.prepareStatement("SELECT computer.id, computer.name,"
            		+ " introduced, discontinued, company_id, company.name AS company_name "
            		+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY computer.id "
            		+ "LIMIT ? OFFSET ?")) {
                statement.setInt(1, page.getMaxLine());
                statement.setInt(2, page.getPageFirstLine());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Computer computer = ComputerMapper.getComputer(resultSet);
                    computerList.add(computer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return computerList;
    }

    @Override
    public Optional<Computer> findById(Long id) {
        Optional<Computer> result = Optional.empty();
        if (id != null) {
            try (PreparedStatement statement = connect.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, "
                    + "company_id, company.name AS company_name FROM computer LEFT JOIN company ON "
                    + "company_id = company.id WHERE computer.id = ? ")) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    result = Optional.ofNullable(ComputerMapper.getComputer(resultSet));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Add a computer in the database.
     * @param computer
     */
    public void create(Computer computer) {
        if (computer != null) {
            try (PreparedStatement statement = connect.prepareStatement("INSERT INTO computer "
            		+ "(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)")) {
                statement.setString(1, computer.getName());
                java.sql.Date sqlIntroducedDate = new java.sql.Date(computer.getIntroducedDate().getTime());
                java.sql.Date sqlDiscontinuedDate = new java.sql.Date(computer.getDiscontinuedDate().getTime());
                statement.setDate(2, sqlIntroducedDate);
                statement.setDate(3, sqlDiscontinuedDate);
                if (computer.getCompany() == null) {
                    statement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    statement.setLong(4, computer.getCompany().getIdCompany());
                }
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update an existing Computer.
     * @param computer
     */
    public void update(Computer computer) {
        try (PreparedStatement statement = connect.prepareStatement("UPDATE computer SET name = ?, "
        		+ "introduced = ?, discontinued = ?, company_id = ? WHERE id = ?")) {
            statement.setString(1, computer.getName());
            java.sql.Date sqlIntroducedDate = new java.sql.Date(computer.getIntroducedDate().getTime());
            java.sql.Date sqlDiscontinuedDate = new java.sql.Date(computer.getDiscontinuedDate().getTime());
            statement.setDate(2, sqlIntroducedDate);
            statement.setDate(3, sqlDiscontinuedDate);
            if (computer.getCompany() == null) {
                statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                statement.setLong(4, computer.getCompany().getIdCompany());
            }
            statement.setLong(5, computer.getIdComputer());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete an existing Computer.
     * @param id
     */
    public void delete(Long id) {
        try (PreparedStatement statement = connect.prepareStatement("DELETE FROM computer WHERE id = ?")) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}