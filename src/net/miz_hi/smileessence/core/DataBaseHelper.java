package net.miz_hi.smileessence.core;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.auth.Account;
import net.miz_hi.smileessence.data.Template;
import net.miz_hi.smileessence.util.LogHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper
{
	public static final String dbName = Client.getApplication().getExternalFilesDir(null) + "/database.db";
	public static final int dbVersion = 2;

	public DataBaseHelper(Context context)
	{
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1)
	{
		try
		{
			TableUtils.createTableIfNotExists(arg1, Account.class);
			LogHelper.print("account table created");
			TableUtils.createTableIfNotExists(arg1, Template.class);
			LogHelper.print("template table created");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			LogHelper.print("error ontable created");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3)
	{
		try
		{
			TableUtils.createTableIfNotExists(arg1, Account.class);
			LogHelper.print("account table created");
			TableUtils.createTableIfNotExists(arg1, Template.class);
			LogHelper.print("template table created");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			LogHelper.print("error ontable created");
		}
	}
}
