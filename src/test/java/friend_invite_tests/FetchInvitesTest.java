package friend_invite_tests;

import commons.beans.UserBean;
import model.communication.IResponse;
import model.communication.gameInviteComms.InvitesListResponse;
import model.gameInviteHandler.GameInviteManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FetchInvitesTest {
    private static UserBean[] users;

    @BeforeClass
    public static void setup(){
        users = new UserBean[5];
        for (int i = 0; i < 5; i++) {
            users[i] = new UserBean(i, "testUser" + i, "testName" + i, "testSurname" + i, "whatevs", null);
        }
    }

    @Test
    public void testResponseCreation(){
        GameInviteManager manager = new GameInviteManager();
        manager.handleInviteRequest(users[0], users[4]);
        manager.handleInviteRequest(users[1], users[4]);
        List<UserBean> list = manager.getAllInvites(users[4]);
        IResponse response = new InvitesListResponse(list);
        assertTrue(response.toJson().contains("["));
    }
}
