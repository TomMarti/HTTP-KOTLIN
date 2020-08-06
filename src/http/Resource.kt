package http

import java.io.File
import java.lang.Exception

class Resource {
    private var folder:String = "C:/server"
    private var default:String = "/index.html"

    public fun get (path:String):String {
        var file:File
        if (path == "/") {
            file = File(this.folder + default)
        } else {
            file = File(this.folder + path)
        }

        if (file.exists()) {
            return file.readText()
        }
        throw Exception("File not exist")
    }
}