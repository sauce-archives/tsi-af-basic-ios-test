import io.appium.java_client.ios.*;
import org.junit.*;
import org.openqa.selenium.remote.*;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class WebTest extends AbstractTest {
	private static String TESTOBJECT_API_KEY_WEB = getEnvOrFail("TESTOBJECT_API_KEY_WEB");
	private static String TESTOBJECT_APP_ID_WEB = getEnvOrDefault("TESTOBJECT_APP_ID_WEB", "1");
	private static String AUTOMATION_NAME = getEnvOrDefault("AUTOMATION_NAME", null);

	// Credentials differ slightly for the web test so we override setup().
	@Before
	@Override
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("testobject_device", TESTOBJECT_DEVICE);
		capabilities.setCapability("testobject_app_id", TESTOBJECT_APP_ID_WEB);
		capabilities.setCapability("testobject_appium_version", TESTOBJECT_APPIUM_VERSION);
		capabilities.setCapability("testobject_cache_device", TESTOBJECT_CACHE_DEVICE);
		if (AUTOMATION_NAME != null) {
			capabilities.setCapability("automationName", AUTOMATION_NAME);
		}

		URL endpoint = new URL(APPIUM_SERVER);

		// We generate a random UUID for later lookup in logs for debugging
		String testUUID = UUID.randomUUID().toString();
		System.out.println("TestUUID: " + testUUID);
		capabilities.setCapability("testobject_testuuid", testUUID);

		driver = new IOSDriver(endpoint, capabilities);

		System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
		System.out.println(driver.getCapabilities().getCapability("testobject_test_live_view_url"));
	}

	@Test
	public void openWebpage() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		String url = "http://adt01m2.mv.appa21.tsi-af.de/ui/web";
		driver.get(url);
	}
}
