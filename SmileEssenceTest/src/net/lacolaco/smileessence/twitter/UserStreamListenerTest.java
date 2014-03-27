/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2014 lacolaco.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.lacolaco.smileessence.twitter;

import android.test.ActivityInstrumentationTestCase2;
import net.lacolaco.smileessence.activity.MainActivity;
import net.lacolaco.smileessence.data.StatusCache;
import net.lacolaco.smileessence.entity.Account;
import net.lacolaco.smileessence.util.TwitterMock;
import twitter4j.Status;
import twitter4j.User;

public class UserStreamListenerTest extends ActivityInstrumentationTestCase2<MainActivity>
{

    TwitterMock mock;
    UserStreamListener listener;

    public UserStreamListenerTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        mock = new TwitterMock(getInstrumentation().getContext());
        listener = new UserStreamListener(getActivity());
    }

    public void testOnStatus() throws Exception
    {
        final Status status = mock.getRetweetMock();
        final String token = mock.getAccessToken();
        final String secret = mock.getAccessTokenSecret();
        final User user = mock.getUserMock();
        getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Account account = new Account(token, secret, user.getId(), user.getScreenName());
                getActivity().setCurrentAccount(account);
                getActivity().initializePages();
                listener.onStatus(status);
            }
        });
        Thread.sleep(1000);
        assertEquals(1, getActivity().getListAdapter(MainActivity.PAGE_HOME).getCount());
        assertEquals(status.getRetweetedStatus(), StatusCache.getInstance().get(status.getRetweetedStatus().getId()));
    }

    public void testOnMention() throws Exception
    {
        final Status status = mock.getStatusMock();
        final String token = mock.getAccessToken();
        final String secret = mock.getAccessTokenSecret();
        final User user = mock.getUserMock();
        getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Account account = new Account(token, secret, user.getId(), user.getScreenName());
                getActivity().setCurrentAccount(account);
                getActivity().initializePages();
                listener.onStatus(status);
            }
        });
        Thread.sleep(1000);
        assertEquals(1, getActivity().getListAdapter(MainActivity.PAGE_MENTIONS).getCount());
        assertEquals(1, getActivity().getListAdapter(MainActivity.PAGE_HISTORY).getCount());
    }

    public void testOnRetweeted() throws Exception
    {
        final Status status = mock.getRetweetMock();
        final String token = mock.getAccessToken();
        final String secret = mock.getAccessTokenSecret();
        final User user = mock.getUserMock();
        getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Account account = new Account(token, secret, user.getId(), user.getScreenName());
                getActivity().setCurrentAccount(account);
                getActivity().initializePages();
                listener.onStatus(status);
            }
        });
        Thread.sleep(1000);
        assertEquals(1, getActivity().getListAdapter(MainActivity.PAGE_HOME).getCount());
        assertEquals(0, getActivity().getListAdapter(MainActivity.PAGE_HISTORY).getCount());
    }
}