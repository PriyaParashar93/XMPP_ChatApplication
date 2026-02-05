package com.example.xmppIntegartion.xmpp

import com.example.xmppIntegartion.model.ChatMessage
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.roster.Roster
import org.jxmpp.jid.impl.JidCreate

object ChatRepository {

    fun sendMessage(to: String, text: String) {
        val chatManager = ChatManager.getInstanceFor(XmppManager.getConnection())
        val jid = JidCreate.entityBareFrom(to)
        val chat = chatManager.chatWith(jid)
        chat.send(text)
    }

    fun listen(onMessage: (ChatMessage) -> Unit) {
        val chatManager = ChatManager.getInstanceFor(XmppManager.getConnection())

        chatManager.addIncomingListener { from, message, _ ->
            onMessage(
                ChatMessage(
                    from.asBareJid().toString(),
                    message.body,
                    false
                )
            )
        }
    }


    fun getRosterUsers(): List<String> {
        val connection = XmppManager.getConnection() ?: return emptyList()

        val roster = Roster.getInstanceFor(connection)
        roster.reloadAndWait()

        return roster.entries.map { it.jid.toString() }
    }
}

