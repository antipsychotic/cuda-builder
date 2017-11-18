package com.nitrograph.cuda.builder.resource.memory.chunk

import com.nitrograph.cuda.code.memory.operation._


sealed trait Owner { owner =>
    def toTarget(chunk: MemoryChunk): Target = {
        owner match {
            case Owner.Host => {
                Target.Host(
                    chunk
                )
            }
            case Owner.Device => {
                Target.Device(
                    chunk
                )
            }
        }
    }
}

object Owner {
    case object Device extends Owner
    case object Host extends Owner
}
