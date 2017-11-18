package com.nitrograph.cuda.builder.resource.memory.manager

import com.nitrograph.cuda.builder._
import com.nitrograph.cuda.builder.resource.memory.chunk._
import com.nitrograph.cuda.code._
import com.nitrograph.cuda.code.structure._
import scala.collection.immutable.Map
import scala.collection.mutable.ArrayBuffer

case class ResourceManager(
    HostHeapMemory: Map[String, MemoryChunk],
    DeviceMemory: Map[String, MemoryChunk]
) {
    def declare: StatementSequence = {
        CUDACode.Sequence(
            statements = ArrayBuffer[CUDACode](
                CUDACode.Structure(
                    name = "resources",
                    members = HostHeapMemory.mapValues(
                        memberChunk => {
                            CUDAStructureMember(
                                memberChunk.name,
                                memberChunk.size.kind
                            )
                        }
                    )
                )
            )
        )
    }
}
