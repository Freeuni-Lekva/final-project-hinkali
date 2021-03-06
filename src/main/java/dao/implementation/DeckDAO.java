package dao.implementation;

import dao.DatabaseUtility;
import dao.interfaces.DeckDAOInterface;
import model.Deck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeckDAO implements DeckDAOInterface {

    public static final String DECK_DAO_ATTR = "decks";

    private PreparedStatement pstmt;

    @Override
    public boolean addDeck(Deck deck) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "INSERT INTO decks (name, image) VALUES (?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, deck.getName());
            pstmt.setString(2, deck.getImage());
            int numInserted = pstmt.executeUpdate();
            if (numInserted == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int deckId = rs.getInt(1);
                deck.setDeckId(deckId);
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
    public Deck getDeck(int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        Deck result = null;
        try {
            String sql = "SELECT * FROM decks WHERE deck_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                result = new Deck(deckId, name, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean removeDeck(int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "DELETE FROM decks WHERE deck_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
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
    public boolean changeDeck(Deck deck) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "UPDATE decks SET name = ?, image = ? WHERE deck_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deck.getName());
            pstmt.setString(2, deck.getImage());
            pstmt.setInt(3, deck.getDeckId());
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
    public boolean addCardToDeck(int deckId, int cardId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "INSERT INTO decks_cards (deck_id, card_id) VALUES (?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            pstmt.setInt(2, cardId);
            int numInserted = pstmt.executeUpdate();
            if (numInserted == 1) {
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
    public boolean removeCardFromDeck(int deckId, int cardId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "DELETE FROM decks_cards WHERE deck_id = ? AND card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            pstmt.setInt(2, cardId);
            int numDeleted = pstmt.executeUpdate();
            if (numDeleted == 1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean chooseDeck(int userId, int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "INSERT INTO users_decks (user_id, deck_id) VALUES (?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, deckId);
            int numInserted = pstmt.executeUpdate();
            if (numInserted == 1) {
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
    public boolean updateUserDeck(int userId, int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "UPDATE users_decks SET deck_id = ? WHERE user_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            pstmt.setInt(2, userId);
            int numChanged = pstmt.executeUpdate();
            if (numChanged == 1) {
                result = true;
            }
        } catch(SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public int getUserDeckId(int userId) {
        Connection conn = DatabaseUtility.getConnection();
        int result = -1;
        try {
            String sql = "SELECT deck_id FROM users_decks WHERE user_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("deck_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeUserDeck(int userId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "DELETE FROM users_decks WHERE user_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            int numDeleted = pstmt.executeUpdate();
            if (numDeleted == 1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Integer> getDeckCardIds(int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        List<Integer> result = new ArrayList<>();
        try {
            String sql = "SELECT card_id FROM decks_cards WHERE deck_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int cardId = rs.getInt("card_id");
                result.add(cardId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Integer> getDecksContainingCard(int cardId) {
        Connection conn = DatabaseUtility.getConnection();
        List<Integer> result = new ArrayList<>();
        try {
            String sql = "SELECT deck_id FROM decks_cards WHERE card_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int deckId = rs.getInt("deck_id");
                result.add(deckId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Integer> getUsersWithDeck(int deckId) {
        Connection conn = DatabaseUtility.getConnection();
        List<Integer> result = new ArrayList<>();
        try {
            String sql = "SELECT user_id FROM users_decks WHERE deck_id = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deckId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                result.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<Deck> getAllDecks() {
        Connection conn = DatabaseUtility.getConnection();
        List<Deck> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM decks";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int deckId = rs.getInt("deck_id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                result.add(new Deck(deckId, name, image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }
}
