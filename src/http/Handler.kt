package http

import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread


class Handler {
    private var client:Socket
    private var reader:Scanner
    private var writer:OutputStream

    constructor (client:Socket) {
        this.client = client
        this.reader = Scanner(client.getInputStream())
        this.writer = client.getOutputStream()
    }

    // client personnal thread
    public fun run () {
        thread {
            try {
                var header:String = this.reader.nextLine()

                println(header)
                var splitedHeader = header.split(" ")
                var methode:String = splitedHeader[0]
                var path:String = splitedHeader[1]
                var version:String = splitedHeader[2]


                var res = Resource()
                var fileContent:String = ""
                try {
                    fileContent = res.get(path)
                    println(fileContent)

                    this.write(Response().get("200 OK", fileContent))
                } catch (ex:Exception) {
                    this.write(Response().get404())
                }
            } catch (ex: Exception) {
                this.write(Response().get500())
            } finally {
                client.close()
            }
        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }
}