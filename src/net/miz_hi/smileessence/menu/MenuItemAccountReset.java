package net.miz_hi.smileessence.menu;

import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.dialog.DialogAdapter;
import android.app.Activity;

public class MenuItemAccountReset extends MenuItemBase
{

	public MenuItemAccountReset(Activity activity, DialogAdapter factory)
	{
		super(activity, factory);
	}

	@Override
	public String getText()
	{
		return "認証情報のリセット";
	}

	@Override
	public void work()
	{
		toast("全ての認証情報をリセットします。再起動してください");
		AuthentificationDB.instance().deleteAll();
		activity.finish();
	}

}
