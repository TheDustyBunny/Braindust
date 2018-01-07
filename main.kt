import java.io.FileNotFoundException
import java.io.FileReader

fun main(args: Array<String>) {
    if(args.isNotEmpty()) {
        try {
            bfInterpret(FileReader(args[0]).readText())
        } catch(e: ArrayIndexOutOfBoundsException) {
            println("An argument must be provided... c'mon man, it's not rocket science!")
        } catch(e: FileNotFoundException) {
            println("A file must be provided: Make sure you spell the name of your file correctly.")
        }
    } else {
        bfRepl()
    }
}