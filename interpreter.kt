import kotlin.system.exitProcess

fun bfInterpret(text: String) {

    var textPosition = 0
    var memoryTape = arrayListOf(0)
    var memoryTapePosition = 0

    while(textPosition < text.length) {

        //move right
        if(text[textPosition] == '>') {
            memoryTapePosition += 1
            if(memoryTape.size <= memoryTapePosition) {
                memoryTape.add(0)
            }
        }

        //move left
        else if(text[textPosition] == '<') {
            memoryTapePosition -= 1
            if(memoryTapePosition < 0) {
                println("Error: Moved off memory tape!")
                exitProcess(1)
            }
        }

        //addition
        else if(text[textPosition] == '+') {
            memoryTape[memoryTapePosition] += 1
            if(memoryTape[memoryTapePosition] > 255) {
                memoryTape[memoryTapePosition] = 0
            }
        }

        //subtraction
        else if(text[textPosition] == '-') {
            memoryTape[memoryTapePosition] -= 1
            if(memoryTape[memoryTapePosition] < 0) {
                memoryTape[memoryTapePosition] = 255
            }
        }

        //print char
        else if(text[textPosition] == '.') {
            print(memoryTape[memoryTapePosition].toChar())
        }

        //print raw number
        else if(text[textPosition] == '\'') {
            print(memoryTape[memoryTapePosition])
        }

        //input
        else if(text[textPosition] == ',') {
            memoryTape[memoryTapePosition] = readLine().toString()[0].toInt()
        }

        //start loop
        else if(text[textPosition] == '[') {
            if(memoryTape[memoryTapePosition] == 0) {
                var openedLoops = 0
                textPosition += 1

                while(textPosition < text.length) {
                    if(text[textPosition] == ']' && openedLoops == 0) {
                        break
                    }

                    else if(text[textPosition] == '[') {
                        openedLoops += 1
                    }

                    else if(text[textPosition] == ']') {
                        openedLoops -= 1
                    }

                    textPosition += 1
                }
            }
        }

        //end loop
        else if(text[textPosition] == ']') {
            if(memoryTape[memoryTapePosition] != 0) {
                var closedLoops = 0
                textPosition -= 1

                while(textPosition >= 0) {
                    if(text[textPosition] == '[' && closedLoops == 0) {
                        break
                    }

                    else if(text[textPosition] == ']') {
                        closedLoops += 1
                    }

                    else if(text[textPosition] == '[') {
                        closedLoops -= 1
                    }

                    textPosition -= 1
                }
            }
        }

        textPosition += 1

    }
}