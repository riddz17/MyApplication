/*
 * Copyright [2016] [Ashish Paul]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zerogravity.myapplication.data.common.model.chat;

import android.text.TextUtils;
import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.data.common.model.Player;


/**
 * Created by ashish on 08-04-2016.
 */
public class Message {
    public static final String CHAT_TYPE_ONE_TO_ONE = "one_to_one";
    public static final String CHAT_TYPE_GROUP = "group";

    public static final String MESSAGE_TYPE_TEXT = "text";
    public static final String MESSAGE_TYPE_IMAGE = "image";
    public static final String MESSAGE_TYPE_VIDEO = "video";
    public static final String MESSAGE_TYPE_AUDIO = "audio";

    public String messageId = "";
    public String timeStamp = "";
    public String chatType = "";
    public String messageType = "";
    public Player senderInfo;
    public Player receiverInfo;
    public Clan groupInfo;

    BaseChat messageDetails;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Player getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(Player senderInfo) {
        this.senderInfo = senderInfo;
    }

    public Player getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(Player receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public Clan getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(Clan groupInfo) {
        this.groupInfo = groupInfo;
    }

    public BaseChat getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(BaseChat messageDetails) {
        this.messageDetails = messageDetails;
    }

    public static boolean validateChatType(String chatType) {
        return !TextUtils.isEmpty(chatType) && (chatType.equals(CHAT_TYPE_GROUP) || chatType
            .equals(CHAT_TYPE_ONE_TO_ONE));

    }
}
