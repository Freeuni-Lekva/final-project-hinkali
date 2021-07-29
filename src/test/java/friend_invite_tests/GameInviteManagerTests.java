package friend_invite_tests;

import commons.beans.UserBean;
import model.communication.IResponse;
import model.communication.gameInviteComms.FailedResponse;
import model.communication.gameInviteComms.SuccessResponse;
import model.communication.gameInviteComms.WaitingResponse;
import model.gameInviteHandler.GameInviteManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameInviteManagerTests {
    private static UserBean[] users;
    private GameInviteManager manager;

    @BeforeClass
    public static void setup(){
        users = new UserBean[5];
        for (int i = 0; i < 5; i++) {
            users[i] = new UserBean(i, "testUser" + i, "testName" + i, "testSurname" + i, "whatevs", null);
        }
    }

    @Before
    public void setupEach(){
        manager = new GameInviteManager();
    }

    @Test
    public void testSimpleInvite(){
        manager.handleInviteRequest(users[0], users[1]);
        List<UserBean> invites = manager.getAllInvites(users[1]);
        assertEquals(users[0], invites.get(0));
    }

    @Test
    public void testInvites(){
        manager.handleInviteRequest(users[0], users[4]);
        manager.handleInviteRequest(users[1], users[4]);
        manager.handleInviteRequest(users[2], users[4]);
        manager.handleInviteRequest(users[3], users[4]);
        assertEquals(4, manager.getAllInvites(users[4]).size());
    }

    @Test
    public void testLeaving(){
        manager.handleInviteRequest(users[0], users[4]);
        manager.handleInviteRequest(users[1], users[4]);
        manager.handleInviteRequest(users[2], users[4]);
        manager.handleInviteRequest(users[3], users[4]);
        System.out.println(manager.getLastInvited());
        IResponse responseA = manager.handleLeaveRequest(users[0]);
        System.out.println(manager.getLastInvited());
        IResponse responseB = manager.handleLeaveRequest(users[1]);
        System.out.println(manager.getLastInvited());
        assertEquals(responseA, responseB);
        assertEquals(2, manager.getAllInvites(users[4]).size());
    }

    @Test
    public void testAccept(){
        manager.handleInviteRequest(users[0], users[1]);
        manager.handleInviteRequest(users[2], users[1]);
        IResponse acceptedResponse = manager.handleAcceptRequest(users[1], users[0]);
        assertTrue(acceptedResponse instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) acceptedResponse;
        assertEquals(GameInviteManager.INITIAL_GAME_ID, successResponse.getGameId());
    }

    @Test
    public void testAwait(){
        manager.handleInviteRequest(users[0], users[1]);
        manager.handleInviteRequest(users[2], users[1]);
        System.out.println(manager.getUsersToGameIds());
        IResponse responseInitial = manager.handleAwaitRequest(users[0]);
        assertTrue(responseInitial instanceof WaitingResponse);
        SuccessResponse acceptedResponse = (SuccessResponse) manager.handleAcceptRequest(users[1], users[0]);

        IResponse acceptedUser0 = manager.handleAwaitRequest(users[0]);
        assertTrue(acceptedUser0 instanceof SuccessResponse);
        assertEquals(acceptedResponse.getGameId(), ((SuccessResponse) acceptedUser0).getGameId());
        IResponse failedUser2 = manager.handleAwaitRequest(users[2]);
        assertTrue(failedUser2 instanceof FailedResponse);
    }

}
