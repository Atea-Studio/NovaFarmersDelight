package fr.ateastudio.farmersdelight.util

import java.util.*
import java.util.function.Predicate

object RecipeMatcher {
    fun <T> findMatches(inputs: List<T>, tests: List<Predicate<T>>): IntArray? {
        val elements = inputs.size
        if (elements != tests.size) {
            return null
        } else {
            val ret = IntArray(elements)
            
            for (x in 0 until elements) {
                ret[x] = -1
            }
            
            val data = BitSet((elements + 2) * elements)
            
            for (x in 0 until elements) {
                var matched = 0
                val offset = (x + 2) * elements
                
                for (y in 0 until elements) {
                    if (!data[y] && tests[x].test(inputs[y])) {
                        data.set(offset + y)
                        ++matched
                    }
                }
                
                if (matched == 0) {
                    return null
                }
                
                if (matched == 1 && !RecipeMatcher.claim(ret, data, x, elements)) {
                    return null
                }
            }
            
            return if (data.nextClearBit(0) >= elements) {
                ret
            } else if (RecipeMatcher.backtrack(data, ret, 0, elements)) {
                ret
            } else {
                null
            }
        }
    }
    
    private fun claim(ret: IntArray, data: BitSet, claimed: Int, elements: Int): Boolean {
        val pending: Queue<Int?> = LinkedList<Int?>()
        pending.add(claimed)
        
        while (pending.peek() != null) {
            val test = pending.poll() as Int
            var offset = (test + 2) * elements
            val used = data.nextSetBit(offset) - offset
            check(!(used >= elements || used < 0)) { "What? We matched something, but it wasn't set in the range of this test! Test: $test Used: $used" }
            
            data.set(used)
            data.set(elements + test)
            ret[used] = test
            
            for (x in 0 until elements) {
                offset = (x + 2) * elements
                if (data[offset + used] && !data[elements + x]) {
                    data.clear(offset + used)
                    var count = 0
                    
                    for (y in offset until offset + elements) {
                        if (data[y]) {
                            ++count
                        }
                    }
                    
                    if (count == 0) {
                        return false
                    }
                    
                    if (count == 1) {
                        pending.add(x)
                    }
                }
            }
        }
        
        return true
    }
    
    private fun backtrack(data: BitSet, ret: IntArray, start: Int, elements: Int): Boolean {
        val test = data.nextClearBit(elements + start) - elements
        if (test >= elements) {
            return true
        } else check(test >= 0) { "This should never happen, negative test in backtrack!" }
        val offset = (test + 2) * elements
        
        for (x in 0 until elements) {
            if (data[offset + x] && !data[x]) {
                data.set(x)
                if (backtrack(data, ret, test + 1, elements)) {
                    ret[x] = test
                    return true
                }
                
                data.clear(x)
            }
        }
        
        return false
    }
}
