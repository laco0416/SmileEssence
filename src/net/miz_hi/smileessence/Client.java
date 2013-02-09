package net.miz_hi.smileessence;

import java.io.File;

import net.miz_hi.smileessence.auth.Account;
import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.core.EnumPreferenceKey;
import net.miz_hi.smileessence.core.PreferenceHelper;
import android.app.Application;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class Client
{
	private static Application app;
	private static Account mainAccount;
	private static PreferenceHelper prefHelper;

	private static int textSize;

	private Client()
	{
	}

	public static void putPreferenceValue(EnumPreferenceKey key, Object value)
	{
		prefHelper.putPreferenceValue(key, value);
	}

	public static <T> T getPreferenceValue(EnumPreferenceKey key)
	{
		return prefHelper.getPreferenceValue(key);
	}

	public static boolean hasAuthedAccount()
	{
		Long lastUsedId = (Long) getPreferenceValue(EnumPreferenceKey.LAST_USED_USER_ID);
		return lastUsedId > 0 && !AuthentificationDB.instance().findAll().isEmpty();
	}

	public static Application getApplication()
	{
		return app;
	}

	public static Account getMainAccount()
	{
		return mainAccount;
	}

	public static void setMainAccount(Account account)
	{
		if (account != null)
		{
			putPreferenceValue(EnumPreferenceKey.LAST_USED_USER_ID, account.getUserId());
		}
		else
		{
			putPreferenceValue(EnumPreferenceKey.LAST_USED_USER_ID, -1L);
		}
		mainAccount = account;
	}

	public static File getApplicationFile(String fileName)
	{
		File file = new File(app.getExternalCacheDir(), fileName);
		return file;
	}

	public static Resources getResource()
	{
		return app.getResources();
	}

	public static int getColor(int resId)
	{
		return getResource().getColor(resId);
	}

	public static int getTextSize()
	{
		return textSize;
	}

	public static void loadPreferences()
	{
		if ((Integer) getPreferenceValue(EnumPreferenceKey.TEXT_SIZE) < 0)
		{
			putPreferenceValue(EnumPreferenceKey.TEXT_SIZE, 10);
		}
		textSize = getPreferenceValue(EnumPreferenceKey.TEXT_SIZE);
	}

	public static void initialize(Application app)
	{
		Client.prefHelper = new PreferenceHelper(PreferenceManager.getDefaultSharedPreferences(app));
		Client.app = app;
		loadPreferences();
	}

	public static final String HOMEPAGE_URL = "http://warotter.web.fc2.com/";
	public static final String T4J_URL = "http://twitter4j.org/";
	public static final String PREF_OAUTH_NAME = "oauth_pref";
	public static final String CALLBACK_OAUTH = "oauth://smileessence";

}
