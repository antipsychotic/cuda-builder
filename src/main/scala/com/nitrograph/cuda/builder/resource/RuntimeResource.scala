package com.nitrograph.cuda.builder.resource

import com.nitrograph.cuda.code._

sealed trait RuntimeResource

sealed trait DeviceRuntimeResource

sealed trait HostRuntimeResource

case class Chunk[T <: RuntimeResource](
    size: ChunkSize
) extends RuntimeResource { chunk =>
    def allocate: CUDACode = {
        MemoryOperation(
            T match {
                case _: HostRuntimeResource => {
                    Target.Host
                }
                case _: DeviceRuntimeResource => {
                    Target.Device
                }
            },
            MemoryOperationKind.Allocation(
                chunk.size.value
            )
        )
    }

    def deallocate: CUDACode = {
        MemoryOperation(
            T match {
                case _: HostRuntimeResource => {
                    Target.Host
                }
                case _: DeviceRuntimeResource => {
                    Target.Device
                }
            },
            MemoryOperationKind.Deallocation
        )
    }
}

case class ChunkSize(value: Int) { size =>
    require(size.value > 0)
}
