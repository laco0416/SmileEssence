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

import android.app.Activity;

import net.lacolaco.smileessence.R;
import net.lacolaco.smileessence.data.StatusCache;
import net.lacolaco.smileessence.notification.Notificator;

import twitter4j.Status;

public class StatusCommandAddToIgnore extends StatusCommand {

    // --------------------------- CONSTRUCTORS ---------------------------

    public StatusCommandAddToIgnore(Activity activity, Status status) {
        super(R.id.key_command_status_add_to_ignore, activity, status);
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    public String getText() {
        return getActivity().getString(R.string.command_status_add_to_ignore);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // -------------------------- OTHER METHODS --------------------------

    @Override
    public boolean execute() {
        StatusCache.getInstance().addToIgnoreStatus(getOriginalStatus().getId());
        Notificator.publish(getActivity(), R.string.notice_add_to_ignore);
        return true;
    }
}
