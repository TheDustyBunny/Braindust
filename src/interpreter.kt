import kotlin.system.exitProcess

object Braindust {
    fun Interpret(text: String) {
        var textPos = 0
        val memoryTape = ShortArray(262144)
        var memoryPointer = 0

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