package http

class Response {
    public final fun get (code:String, data:String):String {
        return "HTTP/1.0 $code\n\n$data"
    }

    public final fun get404 ():String {
        return "HTTP/1.0 404 NOT FOUND"
    }

    public final fun get500 ():String {
        return "HTTP/1.0 500 INTERNAL ERROR"
    }
}