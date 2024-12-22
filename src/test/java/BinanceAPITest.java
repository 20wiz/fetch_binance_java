import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.outlier.BinanceAPI;


import java.io.IOException;

public class BinanceAPITest {

    @Test
    public void testGetBTCPriceInfo() {
        try {
            JSONObject btcInfo = BinanceAPI.getBTCPriceInfo();
            assertNotNull(btcInfo);
            assertTrue(btcInfo.has("lastPrice"));
            assertTrue(btcInfo.has("priceChangePercent"));
        } catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }
}