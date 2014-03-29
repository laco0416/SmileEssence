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

package net.lacolaco.smileessence.command.status;

import android.content.Context;
import net.lacolaco.smileessence.R;
import net.lacolaco.smileessence.entity.Account;
import twitter4j.Status;

public class StatusCommandReply extends StatusCommand
{

    public StatusCommandReply(Context context, Account account, long statusId)
    {
        super(R.id.key_command_status_reply, context, account, statusId);
    }

    @Override
    public String getText()
    {
        return getContext().getString(R.string.command_status_reply);
    }

    @Override
    public boolean execute()
    {
        Status status = tryGetStatus();
        if(status == null)
        {
            //TODO error
            return false;
        }
        //String text = String.format("@%s ", status.getUser().getScreenName());
        return true;
    }
}