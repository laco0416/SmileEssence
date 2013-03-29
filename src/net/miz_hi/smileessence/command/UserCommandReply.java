package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.system.TweetSystem;
import net.miz_hi.smileessence.view.MainActivity;
import net.miz_hi.smileessence.view.TweetView;

public class UserCommandReply extends UserCommand
{

	public UserCommandReply(String userName)
	{
		super(userName);
	}

	@Override
	public String getName()
	{
		return "リプライを送る";
	}

	@Override
	public void workOnUiThread()
	{
		TweetSystem system = TweetSystem.getInstance();
		system.setReply(userName, -1);
		TweetView.getInstance().open();
	}

}
