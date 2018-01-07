import kotlin.system.exitProcess

fun bfRepl() {

    println("Use command '?' for help.")

    var memoryTape = arrayListOf(0)
    var memoryTapePosition = 0

    while(true) {
        println(if(memoryTape.size == 1) "\nMemory tape size: ${memoryTape.size} cell" else "\nSize of memory tape: ${memoryTape.size} cells")
        println("Selected cell: ${memoryTapePosition + 1}\nSelected cell's value: ${memoryTape[memoryTapePosition]}\n")
        print("bf: ")

        val text = readLine().toString()
        var textPosition = 0

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

            //exit process
            else if(text[textPosition] == '!') {
                exitProcess(0)
            }

            //help message
            else if(text[textPosition] == '?') {
                println()
                println("« ! » exit process.")
                println("« ? » show this help message.")
                println("« + » adds 1 to currently selected cell's value.")
                println("« - » subtracts 1 from currently selected cell's value.")
                println("« > » moves main pointer to next cell.")
                println("« < » moves main pointer to the previous cell.")
                println("« . » prints value of currently selected cell in ASCII format.")
                println("« ' » prints value of currently selected cell in numerical format.")
                println("« , » requests input.")
                println("« [ » begins loop.")
                println("« ] » ends loop.")
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
}
