import kotlin.system.exitProcess

fun Braindust.Repl() {

    println("[!] Only 512 cells are available in REPL mode. [!]")
    println("Use command «?» for help.\n")

    val memoryTape = ShortArray(512)
    var memoryPointer = 0
    var textPos: Int

    while(true) {

        println("\nSelected cell: ${memoryPointer + 1}\nSelected cell's value: ${memoryTape[memoryPointer]}\n")
        print("bf: ")

        val text = readLine().toString()
        textPos = 0

        while (textPos < text.length) {
            when (text[textPos]) {
                '>' -> { memoryPointer++; if (memoryTape.lastIndex <= memoryPointer) memoryPointer = 0 }
                '<' -> { memoryPointer--; if (memoryPointer < 0) memoryPointer = memoryTape.lastIndex }
                '+' -> { memoryTape[memoryPointer]++; if (memoryTape[memoryPointer] > 255) memoryTape[memoryPointer] = 0 }
                '-' -> { memoryTape[memoryPointer]--; if (memoryTape[memoryPointer] < 0) memoryTape[memoryPointer] = 255 }
                '.' -> { print(memoryTape[memoryPointer].toChar()) }
                '*' -> { print(memoryTape[memoryPointer]) }
                ',' -> { memoryTape[memoryPointer] = readLine().toString()[0].toShort() }
                '!' -> { exitProcess(memoryTape[memoryPointer].toInt()) }
                '?' -> {
                    println("\n«!» exit process.")
                    println("«?» show this help message.")
                    println("«+» add 1 to currently selected cell's value.")
                    println("«-» subtract 1 from currently selected cell's value.")
                    println("«>» move main pointer to next cell.")
                    println("«<» move main pointer to previous cell.")
                    println("«.» print value of currently selected cell in UTF-16 format.")
                    println("«*» print value of currently selected cell in numerical format.")
                    println("«,» request input.")
                    println("«[» begin loop.")
                    println("«]» end loop.") }
                '[' -> {
                    if (memoryTape[memoryPointer] == 0.toShort()) {
                        var openedLoops = 0
                        textPos++

                        while (textPos < text.length) {
                            if (text[textPos] == ']' && openedLoops == 0) break
                            if (text[textPos] == '[') openedLoops += 1
                            if (text[textPos] == ']') openedLoops -= 1

                            textPos++
                        }
                    }
                }
                ']' -> {
                    if (memoryTape[memoryPointer] != 0.toShort()) {
                        var closedLoops = 0
                        textPos--

                        while (textPos >= 0) {
                            if (text[textPos] == '[' && closedLoops == 0) break
                            if (text[textPos] == ']') closedLoops += 1
                            if (text[textPos] == '[') closedLoops -= 1

                            textPos--
                        }
                    }
                }
            }

            textPos++
        }
    }
}