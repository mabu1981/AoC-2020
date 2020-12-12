class Day10 {
    companion object {
        private val input = """8
40
45
93
147
64
90
125
149
145
111
126
9
146
38
97
103
6
122
34
18
35
96
86
116
29
59
118
102
26
66
17
74
94
5
114
128
1
75
47
141
58
65
100
63
12
53
25
106
136
15
82
22
117
2
80
79
139
7
81
129
19
52
87
115
132
140
88
109
62
73
46
24
69
101
110
16
95
148
76
135
142
89
50
72
41
39
42
56
51
57
127
83
121
33
32
23""".lines().map { it.toInt() }.toMutableList()
        private val testInput = """16
10
15
5
1
11
7
19
6
12
4""".lines().map { it.toInt() }.toMutableList()
    }

    fun run() : Int {
        input.add(0)
        input.add(input.maxOrNull()!! + 3)

        val sortedInput = input.sortedBy { it }
        val deltas = sortedInput.zipWithNext { a, b -> b - a }
        return deltas.count { it == 1 } * deltas.count { it == 3 }
    }

    fun run2() : Long {
        val sortedInput = input.sortedBy { it }

        var count = LongArray(sortedInput.size) { 0L }

        count[sortedInput.size - 1] = 1

        for (i in sortedInput.size - 2 downTo 0) {
            count[i] = count[i + 1] // Direct paths

            if (i + 2 < sortedInput.size && sortedInput[i + 2] <= sortedInput[i] + 3) {
                count[i] += count[i + 2] // Paths two steps away
            }
            if (i + 3 < sortedInput.size && sortedInput[i + 3] <= sortedInput[i] + 3) {
                count[i] += count[i + 3] // Paths three steps away
            }
        }
        return count[0]
    }
}

fun main() {
    println("Advent of Code - Day 10")
    println("Part 1: ${Day10().run()}")
    println("Part 2: ${Day10().run2()}")
}
