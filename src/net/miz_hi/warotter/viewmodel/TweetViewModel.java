package net.miz_hi.warotter.viewmodel;

import gueei.binding.Command;
import gueei.binding.DependentObservable;
import gueei.binding.Observable;
import gueei.binding.observables.IntegerObservable;
import gueei.binding.observables.StringObservable;
import net.miz_hi.warotter.core.EventHandlerActivity;
import net.miz_hi.warotter.core.ViewModel;
import net.miz_hi.warotter.model.Warotter;
import net.miz_hi.warotter.util.AsyncTweetTask;
import twitter4j.StatusUpdate;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class TweetViewModel extends ViewModel
{
	public TweetViewModel()
	{
	}

	public StringObservable text = new StringObservable("");
	public Observable<IBinder> token = new Observable<IBinder>(IBinder.class);
	public IntegerObservable cursorPos = new IntegerObservable();
	public DependentObservable<String> textCount = new DependentObservable<String>(String.class, text)
	{

		@Override
		public String calculateValue(Object... arg0) throws Exception
		{
			return String.valueOf(140 - text.get().length());
		}
	};
	
	@Override
	public void onActivityCreated(EventHandlerActivity activity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityDestroy(EventHandlerActivity activity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onEvent(String eventName, EventHandlerActivity activity)
	{
		return false;		
	}

	public Command commandSubmit = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			String result;
			if (text.isNull() || text.get().length() == 0)
			{
				toast("���͂��Ă�������");
			}
			else
			{
				new AsyncTweetTask(Warotter.getMainAccount(), TweetViewModel.this).execute(new StatusUpdate(text.get()));
				text.set("");
				InputMethodManager imm = (InputMethodManager) Warotter.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(token.get(), 0);
				messenger.raise("toggle", null);
			}
		}
	};

	public Command commandWarota = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			StringBuilder sb = new StringBuilder(text.get());
			sb.insert(cursorPos.get() != null ? cursorPos.get() : 0, "�����^��");
			text.set(sb.toString());

		}
	};

	public Command commandReplace = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			// TODO Auto-generated method stub

		}
	};

	public Command commandTemplate = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			// TODO Auto-generated method stub

		}
	};

	public Command commandMorse = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			// TODO Auto-generated method stub

		}
	};

	public Command commandHashtag = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			// TODO Auto-generated method stub

		}
	};

}