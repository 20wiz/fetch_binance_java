package org.outlier;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class BinanceAPI {
    private static final String API_URL = "https://api.binance.com/api/v3/ticker/24hr?symbol=BTCUSDT";

    public static JSONObject getBTCPriceInfo() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONObject(inline.toString());
    }

    public static void main(String[] args) {
        try {
            JSONObject btcInfo = getBTCPriceInfo();
            System.out.println("BTC Price: " + btcInfo.getString("lastPrice"));
            System.out.println("Price Change: " + btcInfo.getString("priceChangePercent") + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}