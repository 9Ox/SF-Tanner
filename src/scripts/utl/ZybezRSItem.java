package scripts.utl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author warfront1
 */
public class ZybezRSItem {

    private static String Json = "";

    public ZybezRSItem(String ItemName) {
        try {
            Json = readUrl("http://forums.zybez.net/runescape-2007-prices/api/" + ItemName.replaceAll("\\s", "+"));
        } catch (Exception e) {
            Json = "";
        }
    }

    public int getAveragePrice() {
        Pattern pattern = Pattern.compile("(?<=\"average\":\")[0-9]+");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return (Integer.parseInt(matcher.group()));
        }
        return 0;
    }

    public int getRecentHigh() {
        Pattern pattern = Pattern.compile("(?<=\"recent_high\":\")[0-9]+");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return (Integer.parseInt(matcher.group()));
        }
        return 0;
    }

    public int getRecentLow() {
        Pattern pattern = Pattern.compile("(?<=\"recent_low\":\")[0-9]+");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return (Integer.parseInt(matcher.group()));
        }
        return 0;
    }

    public int getHighAlchPrice() {
        Pattern pattern = Pattern.compile("(?<=\"high_alch\":\")[0-9]+");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return (Integer.parseInt(matcher.group()));
        }
        return 0;
    }

    public String getImageUrl() {
        Pattern pattern = Pattern.compile("(?<=\"image\":\")[^\"]*");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return ((matcher.group()).replace("\\", ""));
        }
        return "";
    }

    public String getZybezID() {
        Pattern pattern = Pattern.compile("(?<=\"id\":\")[^\"]*");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return ((matcher.group()).replace("\\", ""));
        }
        return "";
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static String getAllOffersAsJson() {
        Pattern pattern = Pattern.compile("(?<=\"offers\":\\[)[^\\]]*");
        Matcher matcher = pattern.matcher(Json);
        while (matcher.find()) {
            return ((matcher.group()));
        }
        return "";
    }

    public static Map<String, String[]> getAllOffersInMap() {
        Map<String, String[]> ReturnMap = new HashMap<String, String[]>();

        String JsonOffersAsString = getAllOffersAsJson();
        Pattern pattern = Pattern.compile("(?<=\"selling\":\")[0|1]");
        Matcher matcher = pattern.matcher(JsonOffersAsString);
        int sizeofreturn = 0;
        while (matcher.find()) {
            sizeofreturn = sizeofreturn + 1;
        }
        String[] SellingOrBuying = new String[sizeofreturn];
        String[] QuantityArray = new String[sizeofreturn];
        String[] PriceArray = new String[sizeofreturn];
        String[] DateArray = new String[sizeofreturn];
        String[] RSNameArray = new String[sizeofreturn];
        String[] ContactArray = new String[sizeofreturn];
        String[] NotesArray = new String[sizeofreturn];

        int counter = 0;
        pattern = Pattern.compile("(?<=\"selling\":\")[0|1]");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            SellingOrBuying[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"quantity\":\")[0-9]+");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            QuantityArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"price\":\")[0-9]+");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            PriceArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"date\":\")[0-9]+");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            DateArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"rs_name\":\")[^\"]*");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            RSNameArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"contact\":\")[^\"]*");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            ContactArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        counter = 0;
        pattern = Pattern.compile("(?<=\"notes\":\")[^\"]*");
        matcher = pattern.matcher(JsonOffersAsString);
        while (matcher.find()) {
            NotesArray[counter] = ((matcher.group()));
            counter = counter + 1;
        }
        ReturnMap.put("selling", SellingOrBuying);
        ReturnMap.put("quantity", QuantityArray);
        ReturnMap.put("price", QuantityArray);
        ReturnMap.put("date", DateArray);
        ReturnMap.put("rs_name", RSNameArray);
        ReturnMap.put("contact", ContactArray);
        ReturnMap.put("notes", NotesArray);

        return ReturnMap;
    }

    public String getFullJsonReturn() {
        return Json;
    }
}
