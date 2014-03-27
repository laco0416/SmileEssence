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

import net.lacolaco.smileessence.activity.MainActivity;
import net.lacolaco.smileessence.data.StatusCache;
import net.lacolaco.smileessence.notification.Notificator;
import net.lacolaco.smileessence.view.adapter.CustomListAdapter;
import net.lacolaco.smileessence.viewmodel.EnumEvent;
import net.lacolaco.smileessence.viewmodel.EventViewModel;
import net.lacolaco.smileessence.viewmodel.StatusViewModel;
import twitter4j.*;

public class UserStreamListener implements twitter4j.UserStreamListener, ConnectionLifeCycleListener
{

    private final MainActivity activity;

    public UserStreamListener(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onConnect()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDisconnect()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCleanUp()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDeletionNotice(long directMessageId, long userId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFriendList(long[] friendIds)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFavorite(User source, User target, Status favoritedStatus)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnfavorite(User source, User target, Status unfavoritedStatus)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFollow(User source, User followedUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnfollow(User source, User unfollowedUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDirectMessage(DirectMessage directMessage)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListMemberAddition(User addedMember, User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListSubscription(User subscriber, User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListUnsubscription(User subscriber, User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListCreation(User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListUpdate(User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListDeletion(User listOwner, UserList list)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserProfileUpdate(User updatedUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBlock(User source, User blockedUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnblock(User source, User unblockedUser)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStatus(Status status)
    {
        StatusCache.getInstance().put(status);
        if(status.isRetweet())
        {
            StatusCache.getInstance().put(status.getRetweetedStatus());
        }
        CustomListAdapter<?> home = activity.getListAdapter(MainActivity.PAGE_HOME);
        StatusViewModel viewModel = new StatusViewModel(status);
        home.addToTop(viewModel);
        if(status.isRetweet())
        {
            if(viewModel.isRetweetOfMe(activity.getCurrentAccount().userID))
            {
                viewModel.setRetweetOfMe(true);
                CustomListAdapter<?> history = activity.getListAdapter(MainActivity.PAGE_HISTORY);
                EventViewModel retweeted = new EventViewModel(EnumEvent.RETWEETED, status.getUser(), status);
                Notificator notificator = new Notificator(activity, retweeted.getFormattedString(activity));
                notificator.publish();
                history.addToTop(retweeted);
            }
        }
        else if(viewModel.isMention(activity.getCurrentAccount().screenName))
        {
            viewModel.setMention(true);
            CustomListAdapter<?> mentions = activity.getListAdapter(MainActivity.PAGE_MENTIONS);
            mentions.addToTop(viewModel);
            CustomListAdapter<?> history = activity.getListAdapter(MainActivity.PAGE_HISTORY);
            EventViewModel mentioned = new EventViewModel(EnumEvent.MENTIONED, status.getUser(), status);
            Notificator notificator = new Notificator(activity, mentioned.getFormattedString(activity));
            notificator.publish();
            history.addToTop(mentioned);
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStallWarning(StallWarning warning)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onException(Exception ex)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
