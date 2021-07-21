package matchmaking_tests;

import model.matchmaking.BasicMatchmakingQueue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class BasicMatchmakingTest {
    private BasicMatchmakingQueue queue;

    @Before
    public  void setUp(){
        queue = new BasicMatchmakingQueue();
    }

    @Test
    public void testAddSameUserSeveralTimes(){
        String resp1 = queue.processMatchmakingRequestById(0);
        String resp2 = queue.processMatchmakingRequestById(0);
        String resp3 = queue.processMatchmakingRequestById(0);

        assertEquals(BasicMatchmakingQueue.ADDED_TO_QUEUE, resp1);
        assertEquals(resp2, resp3);
    }

    @Test
    public void testAddTwoUsersNoSessionCreation(){
        String respUser1_1 = queue.processMatchmakingRequestById(0);
        String respUser1_2 = queue.processMatchmakingRequestById(0);
        String respUser2_1 = queue.processMatchmakingRequestById(1);

        assertEquals(BasicMatchmakingQueue.ADDED_TO_QUEUE, respUser1_1);
        assertEquals(BasicMatchmakingQueue.WAITING, respUser1_2);
        assertEquals(BasicMatchmakingQueue.ADDED_TO_QUEUE, respUser2_1);
    }

    @Test
    public void testGameSessionCreation(){
        assertEquals(BasicMatchmakingQueue.ADDED_TO_QUEUE, queue.processMatchmakingRequestById(0));
        assertEquals(BasicMatchmakingQueue.ADDED_TO_QUEUE, queue.processMatchmakingRequestById(1));
        assertEquals(BasicMatchmakingQueue.WAITING, queue.processMatchmakingRequestById(0));
        assertEquals("" + (queue.getGame_id_count() - 1), queue.processMatchmakingRequestById(0));
        assertEquals("" + (queue.getGame_id_count() - 1), queue.processMatchmakingRequestById(1));
    }
}
