import java.io.*;
import java.net.*;
import com.sun.ejte.ccl.reporter.*;

/*
 * Unit test for @ServletFilter
 */
public class WebTest {

    private static SimpleReporterAdapter stat
        = new SimpleReporterAdapter("appserv-tests");
    private static final String TEST_NAME = "xml-override-annotation";
    private static final String EXPECTED_RESPONSE = "filterMessage=hello, initParams: n1=v2, ";

    private String host;
    private String port;
    private String contextRoot;

    public WebTest(String[] args) {
        host = args[0];
        port = args[1];
        contextRoot = args[2];
    }
    
    public static void main(String[] args) {
        stat.addDescription("Unit test for xml override annotation");
        WebTest webTest = new WebTest(args);
        webTest.doTest();
        stat.printSummary(TEST_NAME);
    }

    public void doTest() {
        try { 
            invoke("", "/mytest2", 200, EXPECTED_RESPONSE);
            invoke("_ne", "/mytest", 404, null);
        } catch (Exception ex) {
            System.out.println(TEST_NAME + " test failed");
            stat.addStatus(TEST_NAME, stat.FAIL);
            ex.printStackTrace();
        }
    }

    private void invoke(String testPrefix, String urlPattern,
            int expectedCode, String expectedResponse) throws Exception {
        
        String url = "http://" + host + ":" + port + contextRoot
                     + urlPattern;
        HttpURLConnection conn = (HttpURLConnection)
            (new URL(url)).openConnection();

        String testName = TEST_NAME + testPrefix;
        int code = conn.getResponseCode();
        if (code != expectedCode) {
            System.out.println("Unexpected return code: " + code);
            stat.addStatus(testName, stat.FAIL);
        } else {
            if (expectedResponse == null) { // don't check body
                stat.addStatus(testName, stat.PASS);
                return;
            }

            InputStream is = null;
            BufferedReader input = null;
            String line = null;
            try {
                is = conn.getInputStream();
                input = new BufferedReader(new InputStreamReader(is));
                line = input.readLine();
                //System.out.println(line);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch(IOException ioe) {
                    // ignore
                }
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch(IOException ioe) {
                    // ignore
                }
            }
            if (expectedResponse.equals(line)) {
                stat.addStatus(testName, stat.PASS);
            } else {
                System.out.println("Wrong response. Expected: " + 
                        expectedResponse + ", received: " + line);
                stat.addStatus(testName, stat.FAIL);
            }
        }    
    }
}
