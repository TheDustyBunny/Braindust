package dusty.braindust

object Braindust {
    @kotlin.ExperimentalUnsignedTypes
    fun interpret(text: String) {
        var textPos = 0
        val memoryTape = UByteArray(UShort.MAX_VALUE.toInt()) //just using the max value of a ushort because I don't know what number to put here (don't think I'm gonna make the memory tape "infinite")
        var memoryPointer = 0

        while (textPos < text.length) {
            when (text[textPos]) {
                '>' -> { memoryPointer++ }
                '<' -> { memoryPointer-- }
                '+' -> { memoryTape[memoryPointer]++ }
                '-' -> { memoryTape[memoryPointer]-- }
                '.' -> { print(memoryTape[memoryPointer].toByte().toChar()) } //can't convert stuff to unsigned types directly wtf
                ',' -> { memoryTape[memoryPointer] = readLine().toString()[0].toShort().toUByte() } //can't convert a string directly to ubyte lmao
                '[' -> {
                    if (memoryTape[memoryPointer] == 0.toUByte()) {
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
                    if (memoryTape[memoryPointer] != 0.toUByte()) {
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