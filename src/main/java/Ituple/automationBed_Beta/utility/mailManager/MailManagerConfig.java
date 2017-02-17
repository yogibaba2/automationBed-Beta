package Ituple.automationBed_Beta.utility.mailManager;

import java.net.MalformedURLException;
import java.net.URL;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class MailManagerConfig {

	private static MailManagerConfig instance;
	private Config mailConf;

	protected MailManagerConfig() {
		mailConf =ConfigFactory.load(MailManagerConfig.class.getClassLoader(), System.getProperty("mail.properties","mailmanager.properties"));
	}

	public void sendMail(){
		
	}
	
	public static synchronized MailManagerConfig getInstance() {
		if (instance == null) {
			instance = new MailManagerConfig();
		}
		return instance;
	}

	public static String getString(String key) {
		return MailManagerConfig.getInstance().mailConf.getString(key);
	}

	public static int getInt(String key) {
		return MailManagerConfig.getInstance().mailConf.getInt(key);
	}

	public static boolean getBoolean(String key) {
		return MailManagerConfig.getInstance().mailConf.getBoolean(key);
	}

	public static URL getUrl(String key) throws MalformedURLException {
		return new URL(MailManagerConfig.getInstance().mailConf.getString(key));
	}
}
