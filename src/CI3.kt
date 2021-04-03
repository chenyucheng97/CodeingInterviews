
fun main(args: Array<String>) {
    println("-- begin --")
    val result = getDuplicateNumber(intArrayOf(2, 3, 1, 0, 2, 5, 3))
    result?.forEach { println(it) }
    println("-- end --")

    val oneDuplicateNumber = getOneDuplicateNumber(intArrayOf(2, 3, 5, 4, 3, 2, 6, 7))
    println("{2, 3, 5, 4, 3, 2, 6, 7} oneDuplicateNumber = $oneDuplicateNumber")

    val oneDuplicateNumber2 = getOneDuplicateNumber(intArrayOf(2, 2, 5, 4, 3, 2, 6, 7))
    println("{2, 2, 5, 4, 3, 2, 6, 7} oneDuplicateNumber = $oneDuplicateNumber2")


    val oneDuplicateNumber3 = getOneDuplicateNumber(intArrayOf(1, 2, 3, 2))
    println("{1, 2, 3, 2} oneDuplicateNumber = $oneDuplicateNumber3")


    val oneDuplicateNumber4 = getOneDuplicateNumber(intArrayOf(1, 2))
    println("{1,2} oneDuplicateNumber = $oneDuplicateNumber4")

    val oneDuplicateNumber5 = getOneDuplicateNumber(intArrayOf(1, 2, 3, 4))
    println("{1,2，3，4} oneDuplicateNumber = $oneDuplicateNumber5")

    val oneDuplicateNumber6 = getOneDuplicateNumber(intArrayOf(2, 3, 5, 4, 3, 2, 6))
    println("{2, 3, 5, 4, 3, 2, 6} oneDuplicateNumber = $oneDuplicateNumber6")

}

private fun getDuplicateNumber(inputArray: IntArray?): List<Int>? {
    if (inputArray?.isEmpty()!!) {
        return null
    }
    val length = inputArray.size
    val result = ArrayList<Int>()

    for (i in 0 until length) {
        while (inputArray[i] != i) {
            if (inputArray[inputArray[i]] == inputArray[i]) {
                result.add(inputArray[i])
                break
            } else {
                val temp = inputArray[i]
                inputArray[i] = inputArray[temp]
                inputArray[temp] = temp
            }
        }
    }
    return result
}


fun getOneDuplicateNumber(inputArray: IntArray?): Int {
    if (inputArray?.isEmpty()!!) {
        return 0
    }
    val length = inputArray.size
    var start = 1
    var end = length - 1

    while (start <= end) {
        val middle = (start + end) / 2
        val count = countRange(inputArray, start, middle)
        if (start == end) {
            if (count > 1) {
                return start
            } else {
                break
            }
        } else if (start + 1 == end) {
            return if (count > 1) {
                start
            } else if (countRange(inputArray, end, end) > 1) {
                end
            } else {
                break
            }
        }

        if (count <= middle) {
            start = middle + 1
        } else {
            end = middle
        }
    }
    return -1
}


fun countRange(inputArray: IntArray?, start: Int, end: Int): Int {
    if (inputArray?.isEmpty()!!) {
        return 0
    }
    var count = 0
    inputArray.forEach {
        if (it in start..end) {
            count++
        }
    }
    return count
}
