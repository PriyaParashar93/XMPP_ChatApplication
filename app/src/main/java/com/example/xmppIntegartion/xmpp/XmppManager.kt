package com.example.xmppIntegartion.xmpp

import android.os.Handler
import android.os.Looper
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration

object XmppManager {

    private var connection: XMPPTCPConnection? = null

    fun connect(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        Thread {
            try {
                val config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("appinventiv-latitude-5430")
                    .setHost("10.0.2.2") // emulator
                    .setPort(5222)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setUsernameAndPassword(username, password)
                    .build()

                connection = XMPPTCPConnection(config)
                connection!!.connect()
                connection!!.login()

                Handler(Looper.getMainLooper()).post {
                    onSuccess()
                }
            } catch (e: Exception) {
                Handler(Looper.getMainLooper()).post {
                    onError(e.message ?: "XMPP Error")
                }
            }
        }.start()
    }

    fun getConnection() = connection
}

