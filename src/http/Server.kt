package http

import java.net.ServerSocket

class Server {
    private var isStarted:Boolean = false
    private var port = 80

    public fun start () {
        this.isStarted = true
        var socket:ServerSocket = ServerSocket(80)
        println("Server is running on port ${socket.localPort}")

        while (isStarted) {
            var client = socket.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")

            Handler(client).run()
        }
    }

    private fun stop () {
        this.isStarted = false
    }
}