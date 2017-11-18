package com.nitrograph.cuda.code.memory.operation

import com.nitrograph.cuda.code._
import com.nitrograph.cuda.builder.resource.memory.chunk._

enum class Target
object Target {
    case Host(chunk: MemoryChunk)
    case Device(chunk: MemoryChunk)
}
