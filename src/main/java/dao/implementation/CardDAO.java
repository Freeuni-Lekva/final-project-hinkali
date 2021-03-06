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
            String sql = "INSERT INTO cards (name, image, power, position) VALUES (?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getImage());
            pstmt.setInt(3, card.getPower());
            pstmt.setInt(4, card.getRow());
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
                int row = rs.getInt("position");
                result = new Card(cardId, name, image, power, row);
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
            String sql = "UPDATE cards SET name = ?, image = ?, power = ?, position = ? WHERE card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, card.getName());
            pstmt.setString(2, card.getImage());
            pstmt.setInt(3, card.getPower());
            pstmt.setInt(4, card.getRow());
            pstmt.setInt(5, card.getCardId());
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
        if (!cardIds.isEmpty()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM cards WHERE card_id IN (?");
                for (int i = 0; i < cardIds.size() - 1; ++i) {
                    sb.append(", ?");
                }
                sb.append(")");
                String sql = sb.toString();
                pstmt = conn.prepareStatement(sql);
                for (int i = 0; i < cardIds.size(); ++i)
                    pstmt.setInt(i + 1, cardIds.get(i));
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("card_id");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    int power = rs.getInt("power");
                    int row = rs.getInt("position");
                    result.add(new Card(id, name, image, power, row));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseUtility.closeConnection(conn);
            }
        }
        return result;
    }
}
