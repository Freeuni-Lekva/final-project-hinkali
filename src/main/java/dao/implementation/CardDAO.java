package dao.implementation;

import dao.DatabaseUtility;
import dao.interfaces.CardDAOInterface;
import model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements CardDAOInterface {

    public static final String CARD_DAO_ATTR = "cards";

    private PreparedStatement pstmt;

    @Override
    public boolean addCard(Card card) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "INSERT INTO cards (name, image, power) VALUES (?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getImage());
            pstmt.setInt(3, card.getPower());
            int numInserted = pstmt.executeUpdate();
            if (numInserted == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int cardId = rs.getInt(1);
                card.setCardId(cardId);
                result = true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Card getCardById(int cardId) {
        Connection conn = DatabaseUtility.getConnection();
        Card result = null;
        try {
            String sql = "SELECT * FROM cards WHERE card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                int power = rs.getInt("power");
                result = new Card(cardId, name, image, power);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean removeCard(int cardId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "DELETE FROM cards WHERE card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardId);
            int numDeleted = pstmt.executeUpdate();
            if (numDeleted == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean changeCard(Card card) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "UPDATE cards SET name = ?, image = ?, power = ? WHERE card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getImage());
            pstmt.setInt(3, card.getPower());
            pstmt.setInt(4, card.getCardId());
            int numChanged = pstmt.executeUpdate();
            if (numChanged == 1) {
                result = true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Card> getCards(List<Integer> cardIds) {
        Connection conn = DatabaseUtility.getConnection();
        List<Card> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cards WHERE card_id IN (?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setArray(1, conn.createArrayOf("INT", cardIds.toArray()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("card_id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int power = rs.getInt("power");
                result.add(new Card(id, name, image, power));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }
}
